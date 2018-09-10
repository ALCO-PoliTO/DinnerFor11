import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;

import ilog.concert.*;
import ilog.cplex.IloCplex;

public class Main {

	private static void writeDemon() {
		Calendar now = Calendar.getInstance();
		String unique = now.get(Calendar.HOUR_OF_DAY) + "_" + now.get(Calendar.MINUTE) + "-"
				+ ThreadLocalRandom.current().nextInt(0, 100 + 1);
		try {
			File o = new File("out/");
			o.mkdirs();
			FileOutputStream fout = new FileOutputStream("out/Instance_" + unique + ".txt");
			MultiOutputStream multiOut = new MultiOutputStream(System.out, fout);
			PrintStream stdout = new PrintStream(multiOut);
			System.setOut(stdout);
		} catch (FileNotFoundException ex) {
			throw new java.lang.RuntimeException("Output file not found");
		}
	}

	public static void main(String[] args) throws IloException {
		writeDemon();
		IloCplex cp = new IloCplex();
		int Five = 5544;
		int Three = 165;
		int Days = 5;
		int Labels = 11;

		IloIntVar[][] Bingo = new IloIntVar[Five][Days];
		for (int i = 0; i < Five; i++)
			for (int j = 0; j < Days; j++)
				Bingo[i][j] = cp.intVar(0, 1, "Bingo_" + i + "_" + j);

		IloIntVar[][] Triplet = new IloIntVar[Three][Days];
		for (int i = 0; i < Three; i++)
			for (int j = 0; j < Days; j++)
				Triplet[i][j] = cp.intVar(0, 1, "Triplet_" + i + "_" + j);

		int[][][] BingoAdjacency = new int[Five][Labels][Labels];
		for (int i = 0; i < Five; i++)
			for (int j = 0; j < Labels; j++)
				for (int k = 0; k < Labels; k++)
					BingoAdjacency[i][j][k] = 0;

		int[][][] TripletAdjacency = new int[Three][Labels][Labels];
		for (int i = 0; i < Three; i++)
			for (int j = 0; j < Labels; j++)
				for (int k = 0; k < Labels; k++)
					TripletAdjacency[i][j][k] = 0;

		int[][] BingoLabels = new int[Five][Labels];
		int[][] BingoLocations = new int[Five][5];
		for (int i = 0; i < Five; i++)
			for (int j = 0; j < Labels; j++)
				BingoLabels[i][j] = 0;
		for (int i = 0; i < Five; i++)
			for (int j = 0; j < 5; j++)
				BingoLocations[i][j] = 0;

		int[][] TripletLabels = new int[Three][Labels];
		for (int i = 0; i < Three; i++)
			for (int j = 0; j < Labels; j++)
				TripletLabels[i][j] = 0;

		int Count = 0;
		for (int i = 0; i < 7; i++) {
			for (int j = (i + 1); j < 8; j++) {
				for (int k = (j + 1); k < 9; k++) {
					for (int l = (k + 1); l < 10; l++) {
						for (int m = (l + 1); m < 11; m++) {

							// 1 i j k l m
							BingoLabels[Count][i] = 1;
							BingoLocations[Count][0] = i;
							BingoLabels[Count][j] = 1;
							BingoLocations[Count][1] = j;
							BingoLabels[Count][k] = 1;
							BingoLocations[Count][2] = k;
							BingoLabels[Count][l] = 1;
							BingoLocations[Count][3] = l;
							BingoLabels[Count][m] = 1;
							BingoLocations[Count][4] = m;
							Count++;

							// 2 i j k m l
							BingoLabels[Count][i] = 1;
							BingoLocations[Count][0] = i;
							BingoLabels[Count][j] = 1;
							BingoLocations[Count][1] = j;
							BingoLabels[Count][k] = 1;
							BingoLocations[Count][2] = k;
							BingoLabels[Count][m] = 1;
							BingoLocations[Count][3] = m;
							BingoLabels[Count][l] = 1;
							BingoLocations[Count][4] = l;
							Count++;

							// 3 i j l k m
							BingoLabels[Count][i] = 1;
							BingoLocations[Count][0] = i;
							BingoLabels[Count][j] = 1;
							BingoLocations[Count][1] = j;
							BingoLabels[Count][l] = 1;
							BingoLocations[Count][2] = l;
							BingoLabels[Count][k] = 1;
							BingoLocations[Count][3] = k;
							BingoLabels[Count][m] = 1;
							BingoLocations[Count][4] = m;
							Count++;

							// 4 i j l m k
							BingoLabels[Count][i] = 1;
							BingoLocations[Count][0] = i;
							BingoLabels[Count][j] = 1;
							BingoLocations[Count][1] = j;
							BingoLabels[Count][l] = 1;
							BingoLocations[Count][2] = l;
							BingoLabels[Count][m] = 1;
							BingoLocations[Count][3] = m;
							BingoLabels[Count][k] = 1;
							BingoLocations[Count][4] = k;
							Count++;

							// 5 i j m k l
							BingoLabels[Count][i] = 1;
							BingoLocations[Count][0] = i;
							BingoLabels[Count][j] = 1;
							BingoLocations[Count][1] = j;
							BingoLabels[Count][m] = 1;
							BingoLocations[Count][2] = m;
							BingoLabels[Count][k] = 1;
							BingoLocations[Count][3] = k;
							BingoLabels[Count][l] = 1;
							BingoLocations[Count][4] = l;
							Count++;

							// 6 i j m l k
							BingoLabels[Count][i] = 1;
							BingoLocations[Count][0] = i;
							BingoLabels[Count][j] = 1;
							BingoLocations[Count][1] = j;
							BingoLabels[Count][m] = 1;
							BingoLocations[Count][2] = m;
							BingoLabels[Count][l] = 1;
							BingoLocations[Count][3] = l;
							BingoLabels[Count][k] = 1;
							BingoLocations[Count][4] = k;
							Count++;

							// 7 i k j l m
							BingoLabels[Count][i] = 1;
							BingoLocations[Count][0] = i;
							BingoLabels[Count][k] = 1;
							BingoLocations[Count][1] = k;
							BingoLabels[Count][j] = 1;
							BingoLocations[Count][2] = j;
							BingoLabels[Count][l] = 1;
							BingoLocations[Count][3] = l;
							BingoLabels[Count][m] = 1;
							BingoLocations[Count][4] = m;
							Count++;

							// 8 i k j m l
							BingoLabels[Count][i] = 1;
							BingoLocations[Count][0] = i;
							BingoLabels[Count][k] = 1;
							BingoLocations[Count][1] = k;
							BingoLabels[Count][j] = 1;
							BingoLocations[Count][2] = j;
							BingoLabels[Count][m] = 1;
							BingoLocations[Count][3] = m;
							BingoLabels[Count][l] = 1;
							BingoLocations[Count][4] = l;
							Count++;

							// 9 i k l j m
							BingoLabels[Count][i] = 1;
							BingoLocations[Count][0] = i;
							BingoLabels[Count][k] = 1;
							BingoLocations[Count][1] = k;
							BingoLabels[Count][l] = 1;
							BingoLocations[Count][2] = l;
							BingoLabels[Count][j] = 1;
							BingoLocations[Count][3] = j;
							BingoLabels[Count][m] = 1;
							BingoLocations[Count][4] = m;
							Count++;

							// 10 i k m j l
							BingoLabels[Count][i] = 1;
							BingoLocations[Count][0] = i;
							BingoLabels[Count][k] = 1;
							BingoLocations[Count][1] = k;
							BingoLabels[Count][m] = 1;
							BingoLocations[Count][2] = m;
							BingoLabels[Count][j] = 1;
							BingoLocations[Count][3] = j;
							BingoLabels[Count][l] = 1;
							BingoLocations[Count][4] = l;
							Count++;

							// 11 i l j k m
							BingoLabels[Count][i] = 1;
							BingoLocations[Count][0] = i;
							BingoLabels[Count][l] = 1;
							BingoLocations[Count][1] = l;
							BingoLabels[Count][j] = 1;
							BingoLocations[Count][2] = j;
							BingoLabels[Count][k] = 1;
							BingoLocations[Count][3] = k;
							BingoLabels[Count][m] = 1;
							BingoLocations[Count][4] = m;
							Count++;

							// 12 i l k j m
							BingoLabels[Count][i] = 1;
							BingoLocations[Count][0] = i;
							BingoLabels[Count][l] = 1;
							BingoLocations[Count][1] = l;
							BingoLabels[Count][k] = 1;
							BingoLocations[Count][2] = k;
							BingoLabels[Count][j] = 1;
							BingoLocations[Count][3] = j;
							BingoLabels[Count][m] = 1;
							BingoLocations[Count][4] = m;
							Count++;

						}
					}
				}
			}
		}

		Count = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = (i + 1); j < 10; j++) {
				for (int k = (j + 1); k < 11; k++) {
					TripletLabels[Count][i] = 1;
					TripletLabels[Count][j] = 1;
					TripletLabels[Count][k] = 1;
					Count++;
				}

			}
		}

		// One bingo for each day
		IloIntExpr[] BingoPerDay = new IloIntExpr[Days];
		for (int i = 0; i < Days; i++) {
			BingoPerDay[i] = cp.intExpr();
			for (int j = 0; j < Five; j++) {
				BingoPerDay[i] = cp.sum(BingoPerDay[i], Bingo[j][i]);
			}
			cp.addEq(BingoPerDay[i], 1);
		}

		// Two Triplets each day
		IloIntExpr[] TripletsPerDay = new IloIntExpr[Days];
		for (int i = 0; i < Days; i++) {
			TripletsPerDay[i] = cp.intExpr();
			for (int j = 0; j < Three; j++) {
				TripletsPerDay[i] = cp.sum(TripletsPerDay[i], Triplet[j][i]);
			}
			cp.addEq(TripletsPerDay[i], 2);
		}

		// One label per day
		IloIntExpr[][] LabelPerDay = new IloIntExpr[Labels][Days];
		for (int i = 0; i < Days; i++) {
			for (int j = 0; j < Labels; j++) {
				LabelPerDay[j][i] = cp.intExpr();
				
				for (int k = 0; k < Five; k++)
					LabelPerDay[j][i] = cp.sum(LabelPerDay[j][i], cp.prod(BingoLabels[k][j], Bingo[k][i]));

				for (int k = 0; k < Three; k++)
					LabelPerDay[j][i] = cp.sum(LabelPerDay[j][i], cp.prod(TripletLabels[k][j], Triplet[k][i]));

				cp.addEq(LabelPerDay[j][i], 1);
			}
		}

		// Triplets adjacency
		for (int i = 0; i < Three; i++) 
			for (int j = 0; j < Labels; j++) 
				for (int k = 0; k < (Labels - 1); k++) 
					if (TripletLabels[i][j] > 0.5 && TripletLabels[i][k] > 0.5)
						TripletAdjacency[i][j][k] = 1;

		// Bingo adjacency
		for (int i = 0; i < Five; i++) {
			for (int j = 0; j < Labels; j++) {
				for (int k = 0; k < (Labels - 1); k++) {

					if ((BingoLocations[i][0] == j && BingoLocations[i][1] == k)
							|| (BingoLocations[i][0] == k && BingoLocations[i][1] == j))
						BingoAdjacency[i][j][k] = 1;

					if ((BingoLocations[i][1] == j && BingoLocations[i][2] == k)
							|| (BingoLocations[i][1] == k && BingoLocations[i][2] == j))
						BingoAdjacency[i][j][k] = 1;

					if ((BingoLocations[i][2] == j && BingoLocations[i][3] == k)
							|| (BingoLocations[i][2] == k && BingoLocations[i][3] == j))
						BingoAdjacency[i][j][k] = 1;
					
					if ((BingoLocations[i][3] == j && BingoLocations[i][4] == k)
							|| (BingoLocations[i][3] == k && BingoLocations[i][4] == j))
						BingoAdjacency[i][j][k] = 1;
					
					if ((BingoLocations[i][4] == j && BingoLocations[i][0] == k)
							|| (BingoLocations[i][4] == k && BingoLocations[i][0] == j))
						BingoAdjacency[i][j][k] = 1;
				}
			}
		}

		// One adjacency over the days
		IloIntExpr[][] Adjacency = new IloIntExpr[Labels][Labels - 1];
		for (int j = 0; j < Labels; j++) {
			for (int k = 0; k < (Labels - 1); k++) {
				Adjacency[j][k] = cp.intExpr();

				for (int i = 0; i < Five; i++)
					for (int d = 0; d < Days; d++)
						Adjacency[j][k] = cp.sum(Adjacency[j][k], cp.prod(BingoAdjacency[i][j][k], Bingo[i][d]));

				for (int i = 0; i < Three; i++)
					for (int d = 0; d < Days; d++)
						Adjacency[j][k] = cp.sum(Adjacency[j][k], cp.prod(TripletAdjacency[i][j][k], Triplet[i][d]));
				cp.addEq(Adjacency[j][k], 1);

			}
		}

		cp.exportModel("out/DinnerFor11.lp");
		cp.minimize(cp.intVar(0, 42));
		if (cp.solve()) {
			System.out.println("Solved.");
		} else {
			System.out.println("No solution found on OP(3,3,5).");
		}

	}
}
