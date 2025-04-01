package view;

import model.board.HomeBase;
import model.board.Tile;
import model.board.Trap;
import model.pieces.*;
import javax.swing.*;
import java.awt.*;

public class TileButton extends JButton {
    private Tile tile;
    
    private static final Color REGULAR_TILE_COLOR = Color.WHITE;
    private static final Color LAKE_COLOR = Color.BLUE;
    private static final Color TRAP_COLOR = new Color(204,204,204);
    private static final Color HOMEBASE_1_COLOR = new Color(255,102,102);
    private static final Color HOMEBASE_2_COLOR = new Color(51,204,255);
    private static final Color PLAYER_1_COLOR = new Color(255,102,102);
    private static final Color PLAYER_2_COLOR = new Color(51,204,255);

    public TileButton(Tile tile){
        super();
        this.tile = tile;
        this.setBackground(this.getTileColor());
    }

    public Tile getTile(){ // remove?
        return this.tile;
    }

    public void updateTile(Tile tile){
        Piece piece = tile.getCurrentPiece();
        if (piece != null){
            setPieceIcon(piece);
        } else if (tile instanceof model.board.HomeBase && ((model.board.HomeBase)tile).getOwner().getName().equals("Player 1")){
            setTileIcon("Player 1");
        } else if (tile instanceof model.board.HomeBase && ((model.board.HomeBase)tile).getOwner().getName().equals("Player 2")){
            setTileIcon("Player 2");
        } else if (tile instanceof model.board.Trap){
            setTileIcon("Trap");
            this.setBackground(this.getTileColor());
        } else {
            setIcon(null);
            this.setBackground(this.getTileColor());
        }
    }

    private void setPieceIcon(Piece piece){
        String pieceName = piece.getName().toLowerCase();
        String path = "resources\\images\\" + pieceName + ".png"; // Absolute path

        try {
            ImageIcon icon = new ImageIcon(path);

            Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(scaledImage));
        } catch (Exception e){
            System.err.println("Error loading image: " + path);
        }

        if(piece.getOwner().getName() == "Player 1"){
            this.setBackground(PLAYER_1_COLOR);
        }
        else if(piece.getOwner().getName() == "Player 2"){
            this.setBackground(PLAYER_2_COLOR);
        }
    }

    private void setTileIcon(String tileName){
        String path = "resources\\images\\" + tileName + ".png";
        try {
            ImageIcon icon = new ImageIcon(path);

            Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(scaledImage));
        } catch (Exception e){
            System.err.println("Error loading image: " + path);
        }
    }

   private Color getTileColor(){
        if (tile instanceof model.board.Lake){
            return LAKE_COLOR;
        } else if (tile instanceof model.board.Trap){
            return TRAP_COLOR;
        } else if (tile instanceof model.board.HomeBase && ((model.board.HomeBase)tile).getOwner().getName().equals("Player 1")){
            return HOMEBASE_1_COLOR;
        } else if (tile instanceof model.board.HomeBase && ((model.board.HomeBase)tile).getOwner().getName().equals("Player 2")){
            return HOMEBASE_2_COLOR;
        } else {
            return REGULAR_TILE_COLOR;
        }
    }
}
