package elcon.mods.metallurgyaddons.core.util;

import net.minecraft.block.Block;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MAUtilClient {

	public static boolean addBlockDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer, Block block, int metadata) {
		byte size = 4;
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				for(int k = 0; k < size; k++) {
					double xx = (double) x + ((double) i + 0.5D) / (double) size;
					double yy = (double) y + ((double) j + 0.5D) / (double) size;
					double zz = (double) z + ((double) k + 0.5D) / (double) size;
					effectRenderer.addEffect((new EntityDiggingFX(world, xx, yy, zz, xx - (double) x - 0.5D, yy - (double) y - 0.5D, zz - (double) z - 0.5D, block, metadata)).applyColourMultiplier(x, y, z));
				}
			}
		}
		return true;
	}

	public static boolean addBlockHitEffects(World world, MovingObjectPosition target, EffectRenderer effectRenderer, int metadata) {
		int x = target.blockX;
		int y = target.blockY;
		int z = target.blockZ;
		int sideHit = target.sideHit;
		Block block = Block.blocksList[world.getBlockId(x, y, z)];

		float f = 0.1F;
		double xx = (double) x + world.rand.nextDouble() * (block.getBlockBoundsMaxX() - block.getBlockBoundsMinX() - (double) (f * 2.0F)) + (double) f + block.getBlockBoundsMinX();
		double yy = (double) y + world.rand.nextDouble() * (block.getBlockBoundsMaxY() - block.getBlockBoundsMinY() - (double) (f * 2.0F)) + (double) f + block.getBlockBoundsMinY();
		double zz = (double) z + world.rand.nextDouble() * (block.getBlockBoundsMaxZ() - block.getBlockBoundsMinZ() - (double) (f * 2.0F)) + (double) f + block.getBlockBoundsMinZ();
		if(sideHit == 0) {
			yy = (double) y + block.getBlockBoundsMinY() - (double) f;
		}
		if(sideHit == 1) {
			yy = (double) y + block.getBlockBoundsMaxY() + (double) f;
		}
		if(sideHit == 2) {
			zz = (double) z + block.getBlockBoundsMinZ() - (double) f;
		}
		if(sideHit == 3) {
			zz = (double) z + block.getBlockBoundsMaxZ() + (double) f;
		}
		if(sideHit == 4) {
			xx = (double) x + block.getBlockBoundsMinX() - (double) f;
		}
		if(sideHit == 5) {
			xx = (double) x + block.getBlockBoundsMaxX() + (double) f;
		}
		effectRenderer.addEffect((new EntityDiggingFX(world, xx, yy, zz, 0.0D, 0.0D, 0.0D, block, metadata)).applyColourMultiplier(x, y, z).multiplyVelocity(0.2F).multipleParticleScaleBy(0.6F));
		return true;
	}
}
