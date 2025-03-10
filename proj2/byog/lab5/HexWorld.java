package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;

    private static class Position {
        private final int x;
        private final int y;
        private int upHalfX;
        private int upHalfY;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void setUpHalfX(int upHalfX) {
            this.upHalfX = upHalfX;
        }

        public void setUpHalfY(int upHalfY) {
            this.upHalfY = upHalfY;
        }
    }

    private static TERenderer initializeWorld(TETile[][] world, TETile t, int width, int height) {
        TERenderer ter = new TERenderer();
        ter.initialize(width, height);

        return ter;
    }

    private static void computeDrawStartPosition(Position p, int worldWidth, int worldHeight, int size) {
        int hexHeight = size * 2;
        int hexMaxWidth = hexHeight - 2 + size;

        if (hexMaxWidth > worldWidth || hexHeight > worldHeight) {
            throw new IllegalArgumentException("World is too small to draw hexagon");
        }

        int x = p.x - (size - 1);
        int y = p.y + size;
        p.setUpHalfX(x);
        p.setUpHalfY(y);
    }

    private static void drawLine(TETile[][] world, TETile t, int startX, int startY, int width) {
        for (int x = startX; x < startX + width; x++) {
            world[x][startY] = t;
        }
    }

    private static void drawUpHalfHexagon(TETile[][] world, TETile t, Position p, int height, int width) {
        for (int i = 0, x = p.upHalfX, y = p.upHalfY; i < height; i++, x++, y++) {
            drawLine(world, t, x, y, width);
            width -= 2;
        }
    }

    private static void drawLowHalfHexagon(TETile[][] world, TETile t, Position p, int height, int width) {
        for (int i = 0, x = p.x, y = p.y; i < height; i++, x--, y++) {
            drawLine(world, t, x, y, width);
            width += 2;
        }
    }

    private static void drawHexagon(TETile[][] world, TETile t, Position p, int height, int hexMinWidth, int hexMaxWidth) {
        drawLowHalfHexagon(world, t, p, height / 2, hexMinWidth);
        drawUpHalfHexagon(world, t, p, height / 2, hexMaxWidth);
    }

    public static void addHexagon(TETile[][] world, Position p, int size, TETile t) {
        int worldWidth = world.length;
        int worldHeight = world[0].length;
        int hexHeight = size * 2;
        int hexMaxWidth = hexHeight - 2 + size;

        if (hexMaxWidth > worldWidth || hexHeight > worldHeight) {
            throw new IllegalArgumentException("World is too small to draw hexagon");
        }

        computeDrawStartPosition(p, worldWidth, worldHeight, size);

        TERenderer ter = initializeWorld(world, t, worldWidth, worldHeight);
        drawHexagon(world, t, p, hexHeight, size, hexMaxWidth);
        ter.renderFrame(world);
    }

    public static void main(String[] args) {
        Position p = new Position(3, 0);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.WALL;
            }
        }

        addHexagon(world, p, 4, Tileset.FLOWER);
    }
}
