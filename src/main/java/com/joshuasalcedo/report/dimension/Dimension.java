package com.joshuasalcedo.report.dimension;

public final class Dimension {
    private final float width;
    private final float height;

    // Predefined standard paper sizes
    public static final Dimension A4 = new Dimension(595, 842);
    public static final Dimension LETTER = new Dimension(612, 792);
    public static final Dimension LEGAL = new Dimension(612, 1008);

    // Private constructor to prevent instantiation
    private Dimension(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "{\n" +
                "  \"dimension\": {\n" +
                "    \"width\": " + getWidth() + ",\n" +
                "    \"height\": " + getHeight() + "\n" +
                "  }\n" +
                "}";
    }

    public static void main(String[] args) {
        System.out.println("A4 Size: " + Dimension.A4);
        System.out.println("Letter Size: " + Dimension.LETTER);
        System.out.println("Legal Size: " + Dimension.LEGAL);
    }
}