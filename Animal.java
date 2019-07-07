package com.test;


public abstract class Animal {
    protected Boolean isMammal;
    protected Boolean isCarnivorous;

    protected Animal(Boolean isMammal2, Boolean isCarnivorous2) {
        this.isMammal = isMammal2;
        this.isCarnivorous = isCarnivorous2;
    }

    protected Boolean getIsMammal() {
        return isMammal;
    }

    protected Boolean getIsCarnivorous() {
        return isCarnivorous;
    }

    protected abstract String getGreeting();

}

class Dog extends Animal {
    protected Dog() {
        super(true, true);
    }

    @Override
    protected String getGreeting() {
        return "ruff";
    }
}

class Cow extends Animal {
    protected Cow() {
        super(true, false);
    }

    @Override
    protected String getGreeting() {
        return "moo";
    }
}

class Duck extends Animal {
    protected Duck() {
        super(true, false);
    }

    @Override
    protected String getGreeting() {
        return "quack";
    }
}

