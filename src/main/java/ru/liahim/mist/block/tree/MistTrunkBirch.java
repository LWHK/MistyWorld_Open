package ru.liahim.mist.block.tree;

import java.util.ArrayList;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.liahim.mist.api.block.IWettable;
import ru.liahim.mist.api.block.MistBlocks;
import ru.liahim.mist.block.MistBlockBranch;
import ru.liahim.mist.block.MistSoil;
import ru.liahim.mist.block.MistTreeLeaves;
import ru.liahim.mist.block.MistTreeTrunk;
import ru.liahim.mist.block.MistWoodBlock;
import ru.liahim.mist.block.upperplant.MistTinderFungus;
import ru.liahim.mist.common.Mist;
import ru.liahim.mist.util.FacingHelper;
import ru.liahim.mist.util.SoilHelper;
import ru.liahim.mist.world.MistWorld;

public class MistTrunkBirch extends MistTreeTrunk {

	public MistTrunkBirch() {
		super(4.0F, 1, 2, true, true, true, (MistTreeLeaves)MistBlocks.BIRCH_LEAVES, 1, 0, 5, new int[] {2, 2, 2, 3});
	}
	
	@Override
	protected int getSoilDepth(World world, BlockPos rootPos) {
		int s = 0;
		if (world.getBlockState(rootPos.down(2)).getBlock() instanceof MistSoil) s = 3;
		return s;
	}

	@Override
	protected int getMinTrunckLength(World world, BlockPos rootPos, long posRand, int soilDepth) {		
		return 3 + (int)(posRand % 5) % 2;
	}

	@Override
	protected int getMaxTreeHeight(World world, BlockPos rootPos, int minTrunckLength, long posRand, int soilDepth) {		
		return minTrunckLength + 7 + soilDepth + (int)(posRand % 7) % 4;
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(world, pos, state, rand);
		if (!world.isRemote && rand.nextInt(300) == 0 && state.getActualState(world, pos).getValue(SIZE) == 4) {
			BlockPos root = pos.down();
			while (world.getBlockState(root).getBlock() == this) root = root.down();
			root = root.up();
			if (world.getBlockState(root).getValue(DIR) == EnumFacing.WEST) {
				EnumFacing dir = EnumFacing.getHorizontal(rand.nextInt(4));
				if (world.getBlockState(pos.offset(dir)).getMaterial().isReplaceable()) {
					world.setBlockState(pos.offset(dir), MistBlocks.TINDER_FUNGUS.getDefaultState().withProperty(MistTinderFungus.FACING, dir));
				}
			}
		}
	}

	@Override
	protected int canGrowth(World world, BlockPos pos, IBlockState state, int size, EnumFacing dir, ArrayList<EnumFacing> availableGrowthDirection, boolean isBud, int totalLength,
		int branchLength, int firstSizeChangeDistance, int firstBendDistance, int firstBranchDistance, int trunckLength, int minTrunckLength, int maxTrunckLength, int maxTreeHeight,
		ArrayList<Integer> segments, ArrayList<BlockPos> nodes, @Nullable BlockPos fixPos, BlockPos rootPos, IBlockState rootState, BlockPos soilPos, IBlockState soil, Random rand) {
		if (MistWorld.isPosInFog(world, rootPos)) return -1;
		if (!(soil.getBlock() instanceof MistSoil)) return -1;
		int humus = SoilHelper.getHumus(soil);
		if (humus == 3) return -1;
		float temp = world.getBiome(rootPos).getTemperature(rootPos);
		float humi = MistWorld.getHumi(world, rootPos, 0);
		if (soil.getValue(IWettable.WET) && humus > 0) {
			/**Growth speed*/
			if (branchLength == 0 && rand.nextInt((int)Math.ceil(trunckLength * 0.15)) != 0) return isBud ? 1 : 0;
			if (temp < -0.5 || temp > 2 || humi < 5) return 0;
			if (availableGrowthDirection.isEmpty()) return isBud ? 1 : 0;
			/**Is branch*/
			if (branchLength > 0) {
				int trunckSize = world.getBlockState(fixPos).getActualState(world, fixPos).getValue(SIZE);
				if (branchLength < Math.min(trunckSize + (trunckLength >= maxTreeHeight - 5 ? 1 : 0), trunckLength <= minTrunckLength + 1 ? 1 : 2)) return 2;
				else return isBud ? 1 : 0;
			} else if (firstSizeChangeDistance < 5) {
				/**Tree height*/
				if (totalLength < maxTreeHeight) return 2;				
				else return trySetDead(world, rootPos, rootState, soilPos, soil, isBud, rand);
			} else return isBud ? 1 : 0;
		} else if (temp > 0 && temp < 1.5 && humi > 20) {
			makeSoil(world, soilPos, soil, rand);
			return 0;
		}
		else return -1;
	}

	@Override
	protected ArrayList<EnumFacing> chooseGrowthDir(World world, BlockPos pos, IBlockState state, int size, EnumFacing dir, ArrayList<EnumFacing> availableGrowthDirection, boolean isBud,
			int totalLength, int branchLength, int firstSizeChangeDistance, int firstBendDistance, int firstBranchDistance, int trunckLength, int minTrunckLength, int maxTreeHeight,
			ArrayList<Integer> segments, ArrayList<BlockPos> nodes, @Nullable BlockPos fixPos, BlockPos rootPos, IBlockState rootState, Random rand) {
		ArrayList<EnumFacing> growthDir = new ArrayList<EnumFacing>();
		for (EnumFacing face : availableGrowthDirection) {
			if (face == EnumFacing.UP) {
				if (branchLength == 0 || (dir != EnumFacing.UP && rand.nextInt(3) == 0))
					growthDir.add(face);
			} else if (trunckLength == totalLength - branchLength || branchLength > 0 || dir == EnumFacing.UP || !availableGrowthDirection.contains(EnumFacing.UP)) {
				if (branchLength == 0) {
					if ((totalLength > minTrunckLength || !availableGrowthDirection.contains(EnumFacing.UP)) && rand.nextInt(3) > 0) {
						IBlockState downBranch = world.getBlockState((pos.offset(face)).down());
						if (downBranch.getBlock() == this ? getDir(downBranch) != face : true)
						growthDir.add(face);
					}
				} else growthDir.add(face);
			}
		}
		return growthDir;
	}

	@Override
	protected boolean isLeavesRemoved(World world, int totalLength, int branchLength, int minTrunckLength, int trunckLength, int firstBranchDistance, BlockPos pos, BlockPos newPos, EnumFacing dir, Random rand) {
		if (dir != EnumFacing.UP) {
			world.setBlockToAir(pos);
			if (rand.nextBoolean() && checkEnvironment(world, newPos.down(), false))
				world.setBlockState(newPos.down(), this.leaves.getDefaultState().withProperty(MistTreeLeaves.AGE, MistTreeLeaves.EnumAge.EMPTY));
		}
		return false;
	}

	@Override
	protected int newNodeDistance(World world, BlockPos rootPos, IBlockState rootState, int totalLength, int branchLength,
		int firstSizeChangeDistance, int firstBendDistance, int firstBranchDistance, int trunckLength, int minTrunckLength,
		int maxTreeHeight, ArrayList<Integer> segments, Random rand) {
		int rootSize = rootState.getValue(SIZE);
		if (branchLength == 0 && rootSize < 4) {
			int lastSegment = segments.isEmpty() ? totalLength : segments.get(segments.size() - 1);
			if (lastSegment > Math.min(6, 4 + rootSize)) return Math.min(trunckLength, 3 + rootSize);
		}
		return 0;
	}

	@Override
	protected boolean checkEnvironment(World world, BlockPos pos, boolean checkLight) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() == this.leaves && state.getValue(LDIR) == EnumFacing.DOWN) return true;
		return canPlaceBlockAt(world, pos) && (!checkLight || checkLight(world, pos) > 0.45) && !(state.getBlock() instanceof BlockLiquid);
	}

	@Override
	public void generateTree(World world, BlockPos pos, Random rand, boolean checkSnow) {
		if (!world.isRemote) {
			IBlockState downState = world.getBlockState(pos.down());
			if (downState.getBlock() instanceof MistSoil && checkEnvironment(world, pos, false) && world.canBlockSeeSky(pos)) {
				boolean old = false;
				long posRand = MistWorld.getPosRandom(world, pos, 0);
				int soilDepth = getSoilDepth(world, pos);
				int minTrunckLength = getMinTrunckLength(world, pos, posRand, soilDepth);
				int maxTreeHight = getMaxTreeHeight(world, pos, minTrunckLength, posRand, soilDepth);
				BlockPos trunkPos = pos;
				int counter = 0;
				for (int i = 0; i < maxTreeHight; ++i) {
					if (checkEnvironment(world, trunkPos.up(), false)) {
						++counter;
						world.setBlockState(trunkPos, this.getDefaultState(), 2);
						trunkPos = trunkPos.up();
					} else break;
				}
				maxTreeHight = counter;
				int size = 0;
				int nodeNumber = -1;
				for (int i = 0; i < 4; ++i) {
					counter -= this.nodeDistance[i];
					if (counter >= i + 1) {
						++size;
						++nodeNumber;
					} else {
						counter += this.nodeDistance[i];
						break;
					}
				}
				boolean potential = size == 4;
				if (size == 4) SoilHelper.setSoil(world, pos.down(), ((MistSoil)downState.getBlock()).getSoilBlock().getDefaultState(), 2, true, Mist.FLAG);
				boolean node;
				trunkPos = pos;
				BlockPos checkPos;
				ArrayList<BlockPos> branches = new ArrayList<BlockPos>();
				for (int i = 0; i < maxTreeHight; ++i) {
					node = false;
					if (counter == 0 && nodeNumber >= 0) {
						node = true;
						counter = this.nodeDistance[nodeNumber];
						--nodeNumber;
						--size;
					}
					if (i >= minTrunckLength && i < maxTreeHight - 1) {
						for (EnumFacing dir : EnumFacing.HORIZONTALS) {
							if (rand.nextInt(5) > 1) {
								checkPos = trunkPos.offset(dir);
								if (checkEnvironment(world, checkPos, false)) {
									downState = world.getBlockState(checkPos.down());
									if (downState.getBlock() == this ? getDir(downState) != dir : true) {
										if (checkEnvironment(world, checkPos.offset(dir), false)) {
											world.setBlockState(checkPos, this.getDefaultState().withProperty(DIR, dir).withProperty(NODE, true), 2);
											if (i == minTrunckLength || size == 0) createBud(world, checkPos, dir.getOpposite(), potential);
											else branches.add(checkPos);
										}
									}
								}
							}
						}
					}
					/**Age*/
					if (i == 0 && size == 4) {
						int j = rand.nextInt(11);
						if (j == 0) {
							world.setBlockState(trunkPos, this.getDefaultState().withProperty(SIZE, 4).withProperty(DIR, EnumFacing.WEST).withProperty(NODE, node), 2);
							old = true;
						} else if (j < 6) world.setBlockState(trunkPos, this.getDefaultState().withProperty(SIZE, 4).withProperty(DIR, EnumFacing.EAST).withProperty(NODE, node), 2);
						else world.setBlockState(trunkPos, this.getDefaultState().withProperty(SIZE, 4).withProperty(NODE, node), 2);
					} else world.setBlockState(trunkPos, this.getDefaultState().withProperty(SIZE, size).withProperty(NODE, node), 2);
					trunkPos = trunkPos.up();
					--counter;
				}
				trunkPos = trunkPos.down();
				createBud(world, trunkPos, EnumFacing.DOWN, potential);
				if (!branches.isEmpty()) {
					for (BlockPos pos1 : branches) {
						createBranch(world, pos1, rand, potential);
					}
				}
				EnumFacing dir;
				for (int x = -3; x < 4; ++x) {
					for (int y = minTrunckLength; y <= maxTreeHight; ++y) {
						for (int z = -3; z < 4; ++z) {
							checkPos = pos.add(x, y, z);
							downState = world.getBlockState(checkPos);
							if (downState.getBlock() == this.leaves) {
								dir = downState.getValue(LDIR);
								if (dir != EnumFacing.UP && dir != EnumFacing.DOWN) {
									if (MistWorld.getPosRandom(world, checkPos, 2) > 0 && this.checkEnvironment(world, checkPos.down(), false))
										if (potential) world.setBlockState(checkPos.down(), this.leaves.getDefaultState(), 2);
										else world.setBlockState(checkPos.down(), this.leaves.getDefaultState().withProperty(MistTreeLeaves.AGE, MistTreeLeaves.EnumAge.EMPTY), 2);
								}
							}
						}
					}
				}
				if (old) {
					for (BlockPos mushPos = pos.up(); !world.getBlockState(mushPos).getValue(NODE); mushPos = mushPos.up()) {
						for (EnumFacing f : EnumFacing.HORIZONTALS) {
							if (rand.nextInt(24) == 0 && world.getBlockState(mushPos.offset(f)).getMaterial().isReplaceable()) {
								world.setBlockState(mushPos.offset(f), MistBlocks.TINDER_FUNGUS.getDefaultState().withProperty(MistTinderFungus.FACING, f));
							}
						}
					}
				}
				drainZone(world, pos.down(), 2, rand);
				if (checkSnow) checkSnow(world, pos, 3);
			}
		}
	}

	@Override
	public void generateTrunk(World world, BlockPos pos, Random rand, boolean checkSnow) {
		if (!world.isRemote) {
			if (world.getBlockState(pos.down()).getBlock() instanceof MistSoil) {
				EnumFacing face = EnumFacing.getHorizontal(rand.nextInt(4));
				int j = rand.nextInt(4) + 3;
				BlockPos checkPos;
				boolean check = true;
				for (int i = 0; i <= j; ++i) {
					checkPos = pos.offset(face, i);
					if (!world.getBlockState(checkPos).getMaterial().isReplaceable() || !world.isSideSolid(checkPos.down(), EnumFacing.UP)) {
						check = false;
						break;
					}
				}
				if (check) {
					BlockPos branchPos;
					for (int i = 0; i < j; ++i) {
						checkPos = pos.offset(face, i);
						if (world.getBlockState(checkPos.up()).getBlock() == Blocks.DOUBLE_PLANT) world.setBlockToAir(checkPos.up());
						world.setBlockState(checkPos, MistBlocks.BIRCH_BLOCK.getDefaultState().withProperty(MistWoodBlock.AXIS, MistWoodBlock.EnumAxis.fromFacingAxis(face.getAxis())), 2);
						if (checkSnow && world.canSnowAt(checkPos.up(), false)) world.setBlockState(checkPos.up(), Blocks.SNOW_LAYER.getDefaultState());
						if (i > 1) {
							for (EnumFacing branchFace : FacingHelper.NOTDOWN) {
								if (branchFace.getAxis() != face.getAxis() && rand.nextInt(3) == 0) {
									branchPos = checkPos.offset(branchFace);
									if (world.getBlockState(branchPos.offset(face.getOpposite())).getBlock() != MistBlocks.BIRCH_BRANCH) {
										if (world.getBlockState(branchPos).getMaterial().isReplaceable()) {
											if (world.getBlockState(branchPos.up()).getBlock() == Blocks.DOUBLE_PLANT) world.setBlockToAir(branchPos.up());
											world.setBlockState(branchPos, MistBlocks.BIRCH_BRANCH.getDefaultState().withProperty(MistBlockBranch.SIZE, 0).withProperty(MistBlockBranch.AXIS, branchFace.getAxis()), 2);
										}
									}
								}
							}
						}
						for (EnumFacing mushFace : EnumFacing.HORIZONTALS) {
							if (mushFace.getAxis() != face.getAxis()) {
								branchPos = checkPos.offset(mushFace);
								if (rand.nextInt(6) == 0) {
									if (world.getBlockState(branchPos).getMaterial().isReplaceable()) {
										if (world.getBlockState(branchPos.up()).getBlock() == Blocks.DOUBLE_PLANT) world.setBlockToAir(branchPos.up());
										world.setBlockState(branchPos, MistBlocks.TINDER_FUNGUS.getDefaultState().withProperty(MistTinderFungus.FACING, mushFace), 2);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	@Override
	protected ItemStack getBranch() {
		return new ItemStack(MistBlocks.BIRCH_BRANCH);
	}

	@Override
	protected ItemStack getTrunk() {
		return new ItemStack(MistBlocks.BIRCH_BRANCH, 1, 6);
	}

	@Override
	protected ItemStack getBlock() {
		return new ItemStack(MistBlocks.BIRCH_BLOCK);
	}

	@Override
	protected ItemStack getNode() {
		return new ItemStack(MistBlocks.BIRCH_BLOCK, 1, 7);
	}
}