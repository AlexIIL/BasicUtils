package alexiil.utils.config;

public interface IConfigurable {
    public void read(ConfigPart cp);

    /** The argument is the config part that should be written too. */
    public ConfigPart write(ConfigPart cp);
}
