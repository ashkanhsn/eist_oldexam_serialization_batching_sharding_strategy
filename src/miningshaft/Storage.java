package miningshaft;

interface Storage {
    public void write(Gem g);
    public Gem read(int id);
}
