package org.firstinspires.ftc.teamcode.auton;

import android.widget.ArrayAdapter;

import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierCurve;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Path;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.PathChain;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Point;

import java.util.ArrayList;
import java.util.List;

public class PedroPathOptimizedBuilder {
    ArrayList<Point> points = new ArrayList<>();
    ArrayList<BezierCurve> curves = new ArrayList<>();

    public PedroPathOptimizedBuilder() {}

    public BezierCurve buildBezier() {
        return new BezierCurve(points);
    }
    public PathChain buildPathChain() {
        ArrayList<Path> paths = new ArrayList<>();

        for (BezierCurve curve: curves) {
            paths.add(new Path(curve));
        }
        return new PathChain(paths);
    }
}
