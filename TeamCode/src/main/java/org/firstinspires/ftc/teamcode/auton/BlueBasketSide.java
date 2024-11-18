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

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.teleop.Robot;

@Config
@Autonomous(name = "BlueBasketSide", group = "Autonomous")
public class BlueBasketSide extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Starting position of the robot (x = -11.8, y = -61.7, heading = -90 degrees)
        Pose2d initialPose = new Pose2d(-15, -63, Math.toRadians(270)); // 90
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        Robot bot = new Robot(hardwareMap);

        // Define trajectory using Pose2d for simultaneous right and forward movement
        TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose)
                .strafeTo(new Vector2d(-8,-40)) // -8, -45
                .waitSeconds(2.3)
                .strafeTo(new Vector2d(-8, -47))
                //Arm to high speci and back down
                .strafeToLinearHeading(new Vector2d(-49,-43), Math.toRadians(90))
                .waitSeconds(0.5)
                .strafeToLinearHeading(new Vector2d(-49,-38), Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(-48,-44.5), Math.toRadians(45))
                .waitSeconds(5.5)
                //intake
                .strafeToLinearHeading(new Vector2d(-60,-45), Math.toRadians(90))
                .waitSeconds(0.5)
                .strafeToLinearHeading(new Vector2d(-60,-38), Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(-49.5,-43.5), Math.toRadians(45))
                .waitSeconds(5.3)
                .strafeToLinearHeading(new Vector2d(-60,-38), Math.toRadians(135))
                .strafeToLinearHeading(new Vector2d(-49.5,-43.5), Math.toRadians(45))
                .waitSeconds(5.3);


        // Final action to close out the trajectory
        Action trajectoryActionCloseOut = tab1.fresh().build();

        Action waitAndArm = drive.actionBuilder(initialPose)
                .afterTime(0.01, bot.setPidVals(2200,952)) // 1050, 3800
//                .afterTime(0.05, bot.intake(-0.5))
                .afterTime(0.02, telemetryPacket -> {
                    bot.wrist.setPosition(0.5);
                    return false;
                })
                .afterTime(0.8, telemetryPacket -> {
                    bot.intakeLeft.setPower(0.5);
                    bot.intakeRight.setPower(-0.5);
                    return false;
                })
                .afterTime(2.3, telemetryPacket -> {
                    bot.wrist.setPosition(0.5);
                    return false;
                })
                .afterTime(2.8, telemetryPacket -> {
                    bot.wrist.setPosition(0.5);
                    return false;
                })
                .afterTime(2.3, bot.setPidVals(2200,644))
                .afterTime(3.2, telemetryPacket -> {
                    bot.wrist.setPosition(0.5);
                    return false;
                })
                .afterTime(3.7, telemetryPacket -> {
                    bot.wrist.setPosition(0.5);
                    return false;
                })
                .afterTime(4.6, telemetryPacket -> {
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
                    bot.wrist.setPosition(0.07);
                    return false;
                })
                .afterTime(6, telemetryPacket -> {
                    bot.intakeLeft.setPower(1);
                    bot.intakeRight.setPower(-1);
                    return false;
                })
                .afterTime(9.3, telemetryPacket -> {
                    bot.intakeLeft.setPower(0.3);
                    bot.intakeRight.setPower(-0.3);
                    return false;
                })
                .afterTime(11.2, bot.setPidVals(0,0))
                .afterTime(11.5, telemetryPacket -> {
                    bot.wrist.setPosition(0.5);
                    return false;
                })
                .afterTime(11.51,bot.setPidVals(2100,0))
                .afterTime(12.9, bot.setPidVals(2100,6000))
                .afterTime(15.2, telemetryPacket -> {
                    bot.intakeLeft.setPower(-0.4);
                    bot.intakeRight.setPower(0.4);
                    return false;
                })
                .afterTime(15.3, telemetryPacket -> {
                    bot.wrist.setPosition(0.07);
                    return false;
                })
                .afterTime(15.5, bot.setPidVals(2100,0))
                .afterTime(16.1, bot.setPidVals(0,0))
                .afterTime(16.5, telemetryPacket -> {
                    bot.intakeLeft.setPower(1);
                    bot.intakeRight.setPower(-1);
                    return false;
                })
                .afterTime(18.5, telemetryPacket -> {
                    bot.intakeLeft.setPower(0.3);
                    bot.intakeRight.setPower(-0.3);
                    return false;
                })
                .afterTime(19.8, bot.setPidVals(0,0))
                .afterTime(20, telemetryPacket -> {
                    bot.wrist.setPosition(0.5);
                    return false;
                })
                .afterTime(21.55,bot.setPidVals(2100,0))
                .afterTime(22, bot.setPidVals(2100,6000))
                .afterTime(25, telemetryPacket -> {
                    bot.intakeLeft.setPower(-0.4);
                    bot.intakeRight.setPower(0.4);
                    return false;
                })
                .afterTime(25.5, bot.setPidVals(2100,0))
                .afterTime(25.6, telemetryPacket -> {
                    bot.wrist.setPosition(0.07);
                    return false;
                })
                .afterTime(26, telemetryPacket -> {
                    bot.intakeLeft.setPower(1);
                    bot.intakeRight.setPower(-1);
                    return false;
                })
                .afterTime(27, bot.setPidVals(0,0))
                .afterTime(28.5, bot.setPidVals(2100,0))
                .afterTime(29.5, telemetryPacket -> {
                    bot.intakeLeft.setPower(-0.4);
                    bot.intakeRight.setPower(0.4);
                    return false;
                })
                .afterTime(30, bot.setPidVals(0,0))

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


