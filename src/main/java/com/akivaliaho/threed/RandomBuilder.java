package com.akivaliaho.threed;

import java.util.Random;

class RandomBuilder {
    private final Random random;

    RandomBuilder() {
        this.random = new Random();
    }

    double nextDouble() {
        return random.nextDouble();
    }

    boolean yesOrNo() {
        return nextInt(10) < 5;
    }

    int nextInt(int bound) {
        return random.nextInt(bound);
    }
}
