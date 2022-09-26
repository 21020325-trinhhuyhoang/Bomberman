package main;

import entity.Entity;

public class ConllisionChecker {

    GamePanel gp;

    public ConllisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int _entityLeftCol = entityLeftCol;
        int _entityRightCol = entityRightCol ;
        int _entityTopRow = entityTopRow;
        int _entityBottomRow = entityBottomRow;

        //int tileNum1, tileNum2;

        //switch (entity.direction) {
            //case "up":
        /**
         * check all direct.
         */
        //________________________________________________________________________________
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                //tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                //tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.mapConllision[entityLeftCol][entityTopRow] > 0 || gp.tileM.mapConllision[entityRightCol][entityTopRow] > 0) {
                    //entity.collisionOn = true;
                    entity.collisionUp = true;
                }

                entityLeftCol = _entityLeftCol;
                entityRightCol = _entityRightCol ;
                entityTopRow = _entityTopRow;
                entityBottomRow = _entityBottomRow;

                //break;
            //case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                //tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                //tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.mapConllision[entityLeftCol][entityBottomRow] > 0 || gp.tileM.mapConllision[entityRightCol][entityBottomRow] > 0) {
                    //entity.collisionOn = true;
                    entity.collisionDown = true;
                }

                entityLeftCol = _entityLeftCol;
                entityRightCol = _entityRightCol ;
                entityTopRow = _entityTopRow;
                entityBottomRow = _entityBottomRow;
               // break;
            //case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                //tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                //tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.mapConllision[entityLeftCol][entityTopRow] > 0 || gp.tileM.mapConllision[entityLeftCol][entityBottomRow] > 0) {
                    //entity.collisionOn = true;
                    entity.collisionLeft = true;
                }

                entityLeftCol = _entityLeftCol;
                entityRightCol = _entityRightCol ;
                entityTopRow = _entityTopRow;
                entityBottomRow = _entityBottomRow;
                //break;
            //case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                //tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                //tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.mapConllision[entityRightCol][entityTopRow] > 0 || gp.tileM.mapConllision[entityRightCol][entityBottomRow] > 0) {
                    //entity.collisionOn = true;
                    entity.collisionRight = true;
                }
                entityLeftCol = _entityLeftCol;
                entityRightCol = _entityRightCol ;
                entityTopRow = _entityTopRow;
                entityBottomRow = _entityBottomRow;
               // break;
       // }
       //___________________________________________________________________________________________________________
    }
}
