package org.firstinspires.ftc.teamcode.auton;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.teleop.Robot;

@Config
@Autonomous(name = "BlueBasketSide", group = "Autonomous", preselectTeleOp = "TeleopV1")
public class BlueBasketSide extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Starting position of the robot (x = -11.8, y = -61.7, heading = -90 degrees)
        Pose2d initialPose = new Pose2d(-15, -62, Math.toRadians(270)); // 90
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        Robot bot = new Robot(hardwareMap);

        // Define trajectory using Pose2d for simultaneous right and forward movement
        TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose)
                .strafeTo(new Vector2d(-8,-39)) // -8, -45
                .waitSeconds(1.5)
                .strafeTo(new Vector2d(-8, -49))
                //Arm to high speci and back down
                .strafeToLinearHeading(new Vector2d(-49,-48), Math.toRadians(90))
                .waitSeconds(0.5)
                .strafeToLinearHeading(new Vector2d(-49,-38), Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(-49,-52), Math.toRadians(45))
                .waitSeconds(7.5)
                //intake
                .strafeToLinearHeading(new Vector2d(-59,-45), Math.toRadians(90))
                .waitSeconds(0.5)
                .strafeToLinearHeading(new Vector2d(-57.5,-38), Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(-50.5,-49), Math.toRadians(45))
                .waitSeconds(5.3)
                .splineToSplineHeading(new Pose2d(-28,-8, Math.toRadians(0)), Math.toRadians(0));


        // Final action to close out the trajectory
        Action trajectoryActionCloseOut = tab1.fresh().build();

        Action waitAndArm = drive.actionBuilder(initialPose)
                .afterTime(0.01, bot.setPidVals(2200,952)) // 1050, 3800 arm out
//                .afterTime(0.05, bot.intake(-0.5))
                .afterTime(0.02, telemetryPacket -> {
                    bot.wrist.setPosition(0.55);
                    return false;
                })
                .afterTime(1, telemetryPacket -> {//score specimen/outake
                    bot.intakeLeft.setPower(0.5);
                    bot.intakeRight.setPower(-0.5);
                    return false;
                })
                .afterTime(2.3, telemetryPacket -> {
                    bot.wrist.setPosition(0.55);
                    return false;
                })
                .afterTime(2, bot.setPidVals(2200,600))
                .afterTime(3.2, telemetryPacket -> {
                    bot.wrist.setPosition(0.55);
                    return false;
                })
                .afterTime(3.8, telemetryPacket -> {
                    bot.intakeLeft.setPower(-0.5);
                    bot.intakeRight.setPower(0.5);
                    return false;
                })
                .afterTime(4.8, bot.setPidVals(700,0))
                .afterTime(5.8, telemetryPacket -> {
                    bot.intakeLeft.setPower(0);
                    bot.intakeRight.setPower(0);
                    return false;
                })

                .afterTime(6.4, bot.setPidVals(0,0))
                .afterTime(6.9, telemetryPacket -> {
                    bot.wrist.setPosition(0);
                    return false;
                })
                .afterTime(6, telemetryPacket -> {
                    bot.intakeLeft.setPower(1);
                    bot.intakeRight.setPower(-1);
                    return false;
                })
                .afterTime(7.8, telemetryPacket -> {
                    bot.intakeLeft.setPower(0.3);
                    bot.intakeRight.setPower(-0.3);
                    return false;
                })
                .afterTime(8.2, telemetryPacket -> {
                    bot.wrist.setPosition(0.55);
                    return false;
                })
                .afterTime(9.7,bot.setPidVals(2100,0))
                .afterTime(11.2, bot.setPidVals(2100,6500))
                .afterTime(13.9, telemetryPacket -> {
                    bot.intakeLeft.setPower(-0.4);
                    bot.intakeRight.setPower(0.4);
                    return false;
                })
                .afterTime(14.1, telemetryPacket -> {
                    bot.wrist.setPosition(0);
                    return false;
                })
                .afterTime(14.2, bot.setPidVals(2100,0))
                .afterTime(14.8, bot.setPidVals(0,0))
                .afterTime(14.9, telemetryPacket -> {
                    bot.intakeLeft.setPower(1);
                    bot.intakeRight.setPower(-1);
                    return false;
                })
                .afterTime(17.5, telemetryPacket -> {
                    bot.intakeLeft.setPower(0.3);
                    bot.intakeRight.setPower(-0.3);
                    return false;
                })
                .afterTime(19.5, telemetryPacket -> {
                    bot.wrist.setPosition(0.5);
                    return false;
                })
                .afterTime(19.7,bot.setPidVals(2100,0))
                .afterTime(20.2, bot.setPidVals(2100,6500))
                .afterTime(23.2, telemetryPacket -> {
                    bot.intakeLeft.setPower(-0.4);
                    bot.intakeRight.setPower(0.4);
                    return false;
                })
                .afterTime(23.7, bot.setPidVals(2100,0))
                .afterTime(23.8, telemetryPacket -> {
                    bot.wrist.setPosition(0);
                    return false;
                })
                .afterTime(24.2, telemetryPacket -> {
                    bot.intakeLeft.setPower(1);
                    bot.intakeRight.setPower(-1);
                    return false;
                })
                .afterTime(26.2, bot.setPidVals(0,0))
                .afterTime(26.3, telemetryPacket -> {
                    bot.intakeLeft.setPower(0);
                    bot.intakeRight.setPower(0);
                    return false;
                })
                .afterTime(27.2, telemetryPacket -> {
                    bot.wrist.setPosition(1);
                    bot.leftHang.setPosition(1);
                    bot.rightHang.setPosition(1);

                    return false;
                })
//                .afterTime(28.3, bot.setPidVals(1400,1000))
//                .afterTime(28.8, bot.setPidVals(1400,2400))
//                .afterTime(29.5, telemetryPacket -> {
//                    bot.wrist.setPosition(0);
//                    return false;
//                })
                .build();

        bot.slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bot.flip.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        bot.slide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bot.flip.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Wait for the start of the op mode
        waitForStart();
        if (isStopRequested()) return;
        bot.wrist.setPosition(0.5);
        Robot.stopPid = false;

        // Execute the defined trajectory
        Action trajectoryActionChosen = tab1.build();
        Actions.runBlocking(
                new ParallelAction(
                        new SequentialAction(
                                trajectoryActionChosen,
                                trajectoryActionCloseOut),
                        waitAndArm,
                        bot.getPIDAction()
                )
        );
        bot.stopPidAction();
    }
}


