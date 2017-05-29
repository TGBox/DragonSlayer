package depr.old.text.oldChangedVersion;

public class Map {

  public Field[][] feld = new Field[15][15];
  public FieldList fl = new FieldList();

  public void createMap() {

    // klassischer Hintergrund
    for (int k = 0; k < 15; k++) {
      for (int j = 0; j < 15; j++) {
        feld[k][j] = fl.wiese_leer;
      }
    }

    // Smaug
    feld[2][12] = fl.dungeon_smaug_drachenatmen;

    // leere Dungeonfelder
    feld[2][9] = fl.dungeon_leer;
    feld[3][9] = fl.dungeon_leer;
    feld[4][9] = fl.dungeon_leer;
    feld[4][10] = fl.dungeon_leer;
    feld[4][11] = fl.dungeon_leer;
    feld[4][13] = fl.dungeon_leer;

    // Berserker mit Heiltrank
    feld[1][9] = fl.dungeon_berserker_mheiltrank;
    feld[1][10] = fl.dungeon_berserker_mheiltrank;
    feld[1][11] = fl.dungeon_berserker_mheiltrank;
    feld[4][10] = fl.dungeon_berserker_mheiltrank;
    feld[3][11] = fl.dungeon_berserker_mheiltrank;
    feld[3][13] = fl.dungeon_berserker_mheiltrank;
    feld[1][13] = fl.dungeon_berserker_mheiltrank;

    //Berserker mit Stahlschwert
    feld[1][12] = fl.dungeon_berserker_stahlschwert;
    feld[2][11] = fl.dungeon_berserker_stahlschwert;
    feld[2][13] = fl.dungeon_berserker_stahlschwert;
    feld[3][12] = fl.dungeon_berserker_excalibur;

    // Wald mit Goblins
    for (int k = 7; k < 10; k++) {
      for (int j = 4; j < 14; j++) {
        feld[k][j] = fl.wald_goblin_kheiltrank;
      }
    }
    for (int k = 1; k < 7; k++) {
      for (int j = 4; j < 7; j++) {
        feld[k][j] = fl.wald_goblin_kheiltrank;
      }
    }
    for (int k = 9; k < 11; k++) {
      for (int j = 1; j < 6; j++) {
        feld[k][j] = fl.wald_goblin_kheiltrank;
      }
    }

    // Feinde auf der Wiese
    feld[12][3] = fl.wiese_kobold_eisenschwert;
    feld[13][5] = fl.dungeon_berserker_eisenschwert;
    feld[10][7] = fl.wiese_kobold_mheiltrank;
    feld[11][10] = fl.wiese_kobold_mheiltrank;
    feld[13][13] = fl.dungeon_berserker_eisenschwert;
    feld[12][7] = fl.wiese_kobold_eisenschwert;

    // items auf der Wiese
    feld[12][4] = fl.wald_kheiltrank;
    feld[13][4] = fl.wald_kheiltrank;
    feld[11][8] = fl.wald_kheiltrank;
    feld[14][9] = fl.wald_kheiltrank;
    feld[13][10] = fl.wald_kheiltrank;
    feld[11][12] = fl.wald_kheiltrank;

  }

  public String getEnv(int xpos, int ypos) {
    Field aktuellesFeld = feld[xpos][ypos];
    return aktuellesFeld.umgebung;
  }

  public boolean isEnemy(int xpos, int ypos) {
    Field aktuellesFeld = feld[xpos][ypos];
    return aktuellesFeld.enemy == null;
  }

  public Field getField(int xpos, int ypos) {
    return feld[xpos][ypos];
  }
}
