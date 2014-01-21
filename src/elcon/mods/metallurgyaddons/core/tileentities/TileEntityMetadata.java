package elcon.mods.metallurgyaddons.core.tileentities;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;

public class TileEntityMetadata extends TileEntityExtended {
	
	private int meta = 0;
	public boolean droppedBlock = false;

	public TileEntityMetadata() {
	}
	
	public TileEntityMetadata(int meta) {
		setTileMetadata(meta);
	}
	
	public TileEntityMetadata(Integer meta) {
		this(meta.intValue());
	}
	
	@Override
	public boolean canUpdate() {
		return false;
	}
	
	@Override
	public Packet getDescriptionPacket() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		try {
			dos.writeByte(0);
			dos.writeInt(xCoord);
			dos.writeInt(yCoord);
			dos.writeInt(zCoord);

			dos.writeInt(getTileMetadata());
		} catch(Exception e) {
			e.printStackTrace();
		}
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "MetallurgyAddons";
		packet.data = bos.toByteArray();
		packet.length = bos.size();
		packet.isChunkDataPacket = true;
		return packet;
	}
	
	public int getTileMetadata() {
		return meta;
	}

	public void setTileMetadata(int meta) {
		this.meta = meta;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		meta = nbt.getInteger("Metadata");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("Metadata", meta);
	}
}
