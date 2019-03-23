public class Square {

    public static Klotski getNextBoard(Utilities.Direction e, Point current_point, Klotski game) {

        switch (e) {
        case UP: {

            Block current = game.blocks.get(current_point);

            Point up_point = new Point(current.x - 1, current.y);
            Point up_point_right = new Point(current.x - 1, current.y + 1);

            Block up = game.blocks.get(up_point);
            Block up_right = game.blocks.get(up_point_right);

            if (current.x < 1)
                break;

            if (up == null || up_right == null) {
                break;
            }

            // free space up
            if (up.type == 0 && up_right.type == 0) {

                --current.x;

                game.blocks.replace(new Point(current.x + 2, current.y), up);
                game.blocks.replace(new Point(current.x + 2, current.y + 1), up_right);
                game.blocks.replace(up_point, current);
                game.blocks.replace(up_point_right, current);

                return new Klotski(game.constructMap());

            }
            break;
        }

        case DOWN: {

            Block current = game.blocks.get(current_point);

            Point down_point = new Point(current.x + 2, current.y);
            Point down_point_right = new Point(current.x + 2, current.y + 1);

            Block down = game.blocks.get(down_point);
            Block down_right = game.blocks.get(down_point_right);

            if (current.x > 3) {
                break;
            }

            if (down == null || down_point_right == null) {
                break;
            }

            // free space down
            if (down.type == 0 && down_right.type == 0) {

                ++current.x;

                game.blocks.replace(current_point, down);
                game.blocks.replace(new Point(current.x - 1, current.y + 1), down_right);
                game.blocks.replace(down_point, current);
                game.blocks.replace(down_point_right, current);

                return new Klotski(game.constructMap());

            }
            break;
        }

        case LEFT: {

            Block current = game.blocks.get(current_point);
            Point left_point = new Point(current.x, current.y - 1);
            Point left_point_down = new Point(current.x + 1, current.y - 1);

            Block left = game.blocks.get(left_point);
            Block left_down = game.blocks.get(left_point_down);

            if (current.y < 1)
                break;

            if (left == null || left_down == null)
                break;

            // free space left
            if (left.type == 0 && left_down.type == 0) {

                --current.y;

                game.blocks.replace(new Point(current.x, current.y + 2), left);
                game.blocks.replace(new Point(current.x + 1, current.y + 2), left_down);
                game.blocks.replace(left_point, current);
                game.blocks.replace(left_point_down, current);

                return new Klotski(game.constructMap());
            }

            break;
        }

        case RIGHT: {

            Block current = game.blocks.get(current_point);
            Point right_point = new Point(current.x, current.y + 2);
            Point right_point_down = new Point(current.x + 1, current.y + 2);

            Block right = game.blocks.get(right_point);
            Block right_down = game.blocks.get(right_point_down);

            if (current.y > 2) {
                break;
            }

            if (right == null || right_down == null) {
                break;
            }

            // free space left
            if (right.type == 0 && right_down.type == 0) {

                ++current.y;

                game.blocks.replace(current_point, right);
                game.blocks.replace(new Point(current.x + 1, current.y - 1), right_down);
                game.blocks.replace(right_point, current);
                game.blocks.replace(right_point_down, current);

                return new Klotski(game.constructMap());
            }

            break;
        }

        }

        return null;
    }

}