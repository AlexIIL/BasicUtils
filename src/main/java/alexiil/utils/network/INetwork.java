package alexiil.utils.network;

import io.netty.buffer.ByteBuf;

public interface INetwork
	{
		public ByteBuf writeToByteBuf();

		public byte[] writeUpdateToByteBuf();

		public void updateFromByteBuf(byte[] bytes);
	}
