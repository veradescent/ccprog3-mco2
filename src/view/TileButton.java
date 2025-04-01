package view;

import model.board.Tile;
import model.pieces.*;
import javax.swing.*;
import java.awt.*;

public class TileButton extends JButton {
    private Tile tile;

    private static final Color REGULAR_TILE_COLOR = Color.WHITE;
    private static final Color LAKE_COLOR = Color.BLUE;
    private static final Color TRAP_COLOR = Color.GRAY;
    private static final Color HOMEBASE_COLOR = Color.RED;

    public TileButton(Tile tile){
        super();
        this.tile = tile;
        this.setBackground(this.getTileColor());
    }

    public Tile getTile(){
        return this.tile;
    }

    public void updateTile(Tile tile){
        Piece piece = tile.getCurrentPiece();
        if (piece != null){
            setPieceIcon(piece);
        } else {
            setIcon(null);
        }
    }

    private void setPieceIcon(Piece piece){
        String pieceName = piece.getName().toLowerCase();
        String path = "D:/Documents/College Files/1st Year/Term 3/CCPROG3/mco2/resources/images/" + pieceName + ".png"; // Absolute path

        try {
            ImageIcon icon = new ImageIcon(path);

            Image scaledImage = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
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
        } else if (tile instanceof model.board.HomeBase){
            return HOMEBASE_COLOR;
        } else {
            return REGULAR_TILE_COLOR;
        }
    }
}
