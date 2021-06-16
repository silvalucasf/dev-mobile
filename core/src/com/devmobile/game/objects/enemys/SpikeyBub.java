package com.devmobile.game.objects.enemys;

import com.devmobile.game.objects.enemys.types.NoAttack;
import com.devmobile.game.objects.enemys.types.SideToSide;

public class SpikeyBub extends NoAttack {
    private SideToSide movement;
    public SpikeyBub(float x, float y, float range) {
        super("SpikeyBub", 16, 16, x, y);
        movement = new SideToSide(this, range, 30);
    }

    @Override
    public void update() {
        movement.update();
        isFlipped = movement.isMovingRight();
    }
}
