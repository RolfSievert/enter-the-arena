package se.liu.ida.carro311rolsi701.tddd78.carlorolf.friendlycharacters;

import se.liu.ida.carro311rolsi701.tddd78.carlorolf.*;
import se.liu.ida.carro311rolsi701.tddd78.carlorolf.Character;

/**
 * The player that you play as in the game.
 */
public class Player extends Character {
    private Controls controls;

    public Player(final double x, final double y, Controls controls, CollisionHandler collisionHandler, Arena arena) {
        super(new Body(new Vector(x, y), ShapeMaker.getRectangle(1, 1), true), 5, 100, 1, true, "object", collisionHandler, arena);
        this.controls = controls;
        //A unique weapon for Player
        //noinspection AssignmentToSuperclassField
        final double attackSpeed = 0.5;
        final int weaponWidth =20;
        weapon = new Weapon(x, y, weaponWidth, 4 * getWidth() / 5, attackSpeed, this);
        //A unique armor for player
        //noinspection AssignmentToSuperclassField
        armor = new Armor(100, this, arena, Images.getImage("helmet"));
        //Increasing players attack speed compared to standard Character
        //noinspection AssignmentToSuperclassField
        final double playerAttackSpeed = 0.8;
        this.attackSpeed = playerAttackSpeed;
    }

    public Controls getControls() {
        return controls;
    }

    public void movePlayer(Direction direction) {
        if (movingDirection == Direction.NONE || movingDirection == direction) movingDirection = direction;
        else {
            if (movingDirection == Direction.WEST) {
                if (direction == Direction.NORTH) movingDirection = Direction.NORTHWEST;
                else if (direction == Direction.SOUTH) movingDirection = Direction.SOUTHWEST;
                else movingDirection = direction;

            } else if (movingDirection == Direction.EAST) {
                if (direction == Direction.NORTH) movingDirection = Direction.NORTHEAST;
                else if (direction == Direction.SOUTH) movingDirection = Direction.SOUTHEAST;
                else movingDirection = direction;

            } else if (movingDirection == Direction.NORTH) {
                if (direction == Direction.EAST) movingDirection = Direction.NORTHEAST;
                else if (direction == Direction.WEST) movingDirection = Direction.NORTHWEST;
                else movingDirection = direction;

            } else if (movingDirection == Direction.SOUTH) {
                if (direction == Direction.WEST) movingDirection = Direction.SOUTHWEST;
                else if (direction == Direction.EAST) movingDirection = Direction.SOUTHEAST;
                else movingDirection = direction;

            }
        }
    }

    public void stopMovingInDirection(Direction dir) {
        if (movingDirection == Direction.NORTHEAST) {
            if (dir == Direction.EAST) movingDirection = Direction.NORTH;
            else if (dir == Direction.NORTH) movingDirection = Direction.EAST;

        } else if (movingDirection == Direction.NORTHWEST) {
            if (dir == Direction.WEST) movingDirection = Direction.NORTH;
            else if (dir == Direction.NORTH) movingDirection = Direction.WEST;

        } else if (movingDirection == Direction.SOUTHEAST) {
            if (dir == Direction.EAST) movingDirection = Direction.SOUTH;
            else if (dir == Direction.SOUTH) movingDirection = Direction.EAST;

        } else if (movingDirection == Direction.SOUTHWEST) {
            if (dir == Direction.SOUTH) movingDirection = Direction.WEST;
            else if (dir == Direction.WEST) movingDirection = Direction.SOUTH;

        } else {
            if (dir == movingDirection) movingDirection = Direction.NONE;
        }
    }

    @Override
    protected void move(double movementSpeed) {
        if (movingDirection.getX() != 0 && movingDirection.getY() == 0 || movingDirection.getX() == 0 && movingDirection.getY() != 0) {
            addX(movingDirection.getX() * movementSpeed);
            addY(movingDirection.getY() * movementSpeed);
        } else {
            addX(Math.sqrt(Math.pow(movementSpeed, 2) / 2) * movingDirection.getX());
            addY(Math.sqrt(Math.pow(movementSpeed, 2) / 2) * movingDirection.getY());
        }
    }

    public void hit() {
        if (movingDirection != Direction.NONE && canAttack) {
            startAttackDelay();
            double wX;
            double wY;
            double weaponRange = weapon.getRange();

            if (movingDirection.getX() != 0 && movingDirection.getY() == 0 || movingDirection.getX() == 0 && movingDirection.getY() != 0) {
                wX = getX() + ((getWidth() + weaponRange) / 2) * movingDirection.getX();
                wY = getY() + ((getHeight() + weaponRange) / 2) * movingDirection.getY();
            } else {
                wX = getX() + movingDirection.getX() * (Math.sqrt(Math.pow(weaponRange, 2) / 2));
                wY = getY() + movingDirection.getY() * (Math.sqrt(Math.pow(weaponRange, 2) / 2));
            }
            weapon.setHittingDirection(movingDirection, wX, wY);
            collisionHandler.addWeapon(weapon);
        }
    }

    public void revive(){
        if (dead) {
            hp = 2*maximumHp/3;
            dead = false;
            arena.addObject(this);
        }
    }
}
