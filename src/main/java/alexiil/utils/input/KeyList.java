package alexiil.utils.input;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import alexiil.utils.network.IMessageSender;
import alexiil.utils.network.messages.MessageKeyUpdate;

public class KeyList {
    public static final KeyList mainList = new KeyList();

    public KeyList() {}

    /** @param parent The parent to add a duplicate of all the keys from */
    public KeyList(KeyList parent) {
        for (Entry<Integer, IKeyInput> entry : parent.keyInteger.entrySet()) {
            IKeyInput k = entry.getValue();
            IKeyInput duplicate;
            if (keyString.containsKey(k.getName()))
                duplicate = keyString.get(k.getName());
            else {
                duplicate = k.duplicate();
                keyString.put(duplicate.getName(), duplicate);
            }
            keyInteger.put(entry.getKey(), duplicate);
        }
    }

    private IMessageSender sender;
    private Map<String, IKeyInput> keyString = Collections.synchronizedMap(new HashMap<String, IKeyInput>());
    private Map<Integer, IKeyInput> keyInteger = Collections.synchronizedMap(new HashMap<Integer, IKeyInput>());

    /** @param name The name of the key (to be saved in the config, and what to get it with)
     * @param k the key input type to add */
    public void addKey(String name, IKeyInput k) {
        if (k == null || name == null)
            throw new Error("The key or the name was null");
        if (keyString.containsKey(name))
            return;// throw new Error("The key already exists (" + name + ")");
        if (keyInteger.containsKey(k.getID()))
            return;// throw new Error("The ID has already been registered");
        keyString.put(name, k);
        keyInteger.put(k.getID(), k);
    }

    public void onKeyPress(int id) {
        if (keyInteger.containsKey(id)) {
            keyInteger.get(id).onPressed();
            sendUpdate(id);
        }
    }

    public void onKeyRelease(int id) {
        if (keyInteger.containsKey(id)) {
            keyInteger.get(id).onRelease();
            sendUpdate(id);
        }
    }

    private void sendUpdate(int id) {
        if (sender == null)
            return;
        IKeyInput k = keyInteger.get(id);
        sender.sendMessage(new MessageKeyUpdate(k, k.getName()));
    }

    public IKeyInput getKey(String name) {
        return keyString.getOrDefault(name, null);
    }

    public void setMessageSender(IMessageSender sender) {
        if (this.sender != null)
            return;
        this.sender = sender;
    }
}
