/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Quarter2.Cytophobia;

import java.awt.*;
import java.util.Hashtable;

public class GraphPaperLayout implements LayoutManager2 {

    private Dimension gridSize;
    private Hashtable<Component, Rectangle> compTable = new Hashtable<>();

    public GraphPaperLayout(Dimension gridSize) {
        this.gridSize = gridSize;
    }

    @Override
    public void addLayoutComponent(Component comp, Object constraints) {
        if (constraints instanceof Rectangle)
            compTable.put(comp, (Rectangle) constraints);
        else
            compTable.put(comp, new Rectangle(0, 0, 1, 1));
    }

    @Override
    public void addLayoutComponent(String name, Component comp) {}

    @Override
    public void removeLayoutComponent(Component comp) {
        compTable.remove(comp);
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return parent.getSize();
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return new Dimension(100, 100);
    }

    @Override
    public void layoutContainer(Container parent) {
        synchronized (parent.getTreeLock()) {
            int n = parent.getComponentCount();
            int width = parent.getWidth();
            int height = parent.getHeight();

            int cellWidth = width / gridSize.width;
            int cellHeight = height / gridSize.height;

            for (int i = 0; i < n; i++) {
                Component comp = parent.getComponent(i);
                Rectangle rect = compTable.get(comp);
                if (rect != null) {
                    int x = rect.x * cellWidth;
                    int y = rect.y * cellHeight;
                    int w = rect.width * cellWidth;
                    int h = rect.height * cellHeight;
                    comp.setBounds(x, y, w, h);
                }
            }
        }
    }

    @Override
    public Dimension maximumLayoutSize(Container target) {
        return preferredLayoutSize(target);
    }

    @Override
    public float getLayoutAlignmentX(Container target) {
        return 0.5f;
    }

    @Override
    public float getLayoutAlignmentY(Container target) {
        return 0.5f;
    }

    @Override
    public void invalidateLayout(Container target) {}
}
