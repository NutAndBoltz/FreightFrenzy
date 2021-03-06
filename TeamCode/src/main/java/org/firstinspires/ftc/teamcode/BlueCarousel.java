package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

//ignore this


//import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
//import org.opencv.core.Core;
//import org.opencv.core.Mat;
//import org.opencv.core.Point;
//import org.opencv.core.Rect;
//import org.opencv.core.Scalar;
//import org.opencv.imgproc.Imgproc;
//import org.openftc.easyopencv.OpenCvCamera;
//import org.openftc.easyopencv.OpenCvCameraFactory;
//import org.openftc.easyopencv.OpenCvCameraRotation;
//import org.openftc.easyopencv.OpenCvPipeline;


    @Autonomous(name="Blue-Carousel", group="Pushbot")
    public class BlueCarousel extends LinearOpMode {

        public robotInit robot = new robotInit();
        ElapsedTime runtime = new ElapsedTime();


        @Override
        public void runOpMode() {

            robot.init(hardwareMap);

            resetEncoder();
            startEncoderMode();


            // Wait for the game to start (driver presses PLAY)
            telemetry.addLine("Waiting for start");
            telemetry.update();
            waitForStart();


            // STEP 1 - Delivering duck on carousel

            //Go towards carousel wheel
            strafeRight(20.6);
            turnLeft(19);
            moveBackward(7);

            //Touch wheel to carousel in order to spin it
            robot.spinnyThing.setPower(0.6);

            runtime.reset();
            while (opModeIsActive() && (runtime.seconds() < 4)) {
                telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
                telemetry.update();
            }

            robot.spinnyThing.setPower(0);

            //head to the storage unit
            turnRight(23);
            moveForward(28);
            turnRight(15);
            //moveForward(2);


            //                                                         NOTE: CODE TO GO TO WAREHOUSE ONLY
//        turnLeft(25);
//        moveForward(60);


//            // Navigate toward carousel
//            turnRight(23);
//            moveForward(29.0);
//            turnLeft(4);
//
//            // Touch wheel to carousel in order to spin it
//            robot.spinnyThing.setPower(-0.3);
//
//            runtime.reset();
//            while (opModeIsActive() && (runtime.seconds() < 2.5)) {
//                telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
//                telemetry.update();
//            }
//
//            robot.spinnyThing.setPower(0);
//
//            // STEP 2 - Place the preloaded block on alliance shipping hub
//
//            // Go toward hub and place block on hub
////            turnLeft(39);
////            moveForward(40);
////            placeFreight();
//
//            // STEP 3 - Park robot in warehouse
//            turnLeft(47);
//            moveForward(140);

        }



        // FUNCTION TO TURN RIGHT
        public void turnRight(double inches) {
            int newmotorFLTarget;
            int newmotorFRTarget;
            int newmotorBLTarget;
            int newmotorBRTarget;

            // Determine new target position, and pass to motor controller
            newmotorFLTarget = robot.motorFL.getCurrentPosition() - (int) (inches * robot.COUNTS_PER_INCH);
            newmotorFRTarget = robot.motorFR.getCurrentPosition() + (int) (inches * robot.COUNTS_PER_INCH);
            newmotorBLTarget = robot.motorBL.getCurrentPosition() - (int) (inches * robot.COUNTS_PER_INCH);
            newmotorBRTarget = robot.motorBR.getCurrentPosition() + (int) (inches * robot.COUNTS_PER_INCH);

            robot.motorFL.setTargetPosition(newmotorFLTarget);
            robot.motorFR.setTargetPosition(newmotorFRTarget);
            robot.motorBL.setTargetPosition(newmotorBLTarget);
            robot.motorBR.setTargetPosition(newmotorBRTarget);

            // Turn On RUN_TO_POSITION
            // robot moves to set position
            robot.motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.motorFL.setPower(Math.abs(robot.DRIVE_SPEED));
            robot.motorFR.setPower(Math.abs(robot.DRIVE_SPEED));
            robot.motorBL.setPower(Math.abs(robot.DRIVE_SPEED));
            robot.motorBR.setPower(Math.abs(robot.DRIVE_SPEED));
            runtime.reset();
            while (opModeIsActive() && (robot.motorFL.isBusy() || robot.motorFR.isBusy() || robot.motorBL.isBusy() || robot.motorBR.isBusy())) {
                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d", newmotorFLTarget, newmotorFRTarget);
                telemetry.update();
            }
        }



        // FUNCTION TO TURN LEFT
        public void turnLeft(double inches) {
            int newmotorFLTarget;
            int newmotorFRTarget;
            int newmotorBLTarget;
            int newmotorBRTarget;

            // Determine new target position, and pass to motor controller
            newmotorFLTarget = robot.motorFL.getCurrentPosition() + (int) (inches * robot.COUNTS_PER_INCH);
            newmotorFRTarget = robot.motorFR.getCurrentPosition() - (int) (inches * robot.COUNTS_PER_INCH);
            newmotorBLTarget = robot.motorBL.getCurrentPosition() + (int) (inches * robot.COUNTS_PER_INCH);
            newmotorBRTarget = robot.motorBR.getCurrentPosition() - (int) (inches * robot.COUNTS_PER_INCH);

            robot.motorFL.setTargetPosition(newmotorFLTarget);
            robot.motorFR.setTargetPosition(newmotorFRTarget);
            robot.motorBL.setTargetPosition(newmotorBLTarget);
            robot.motorBR.setTargetPosition(newmotorBRTarget);

            // Turn On RUN_TO_POSITION
            // robot moves to set position
            robot.motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.motorFL.setPower(Math.abs(robot.DRIVE_SPEED));
            robot.motorFR.setPower(Math.abs(robot.DRIVE_SPEED));
            robot.motorBL.setPower(Math.abs(robot.DRIVE_SPEED));
            robot.motorBR.setPower(Math.abs(robot.DRIVE_SPEED));
            runtime.reset();
            while (opModeIsActive() && (robot.motorFL.isBusy() || robot.motorFR.isBusy() || robot.motorBL.isBusy() || robot.motorBR.isBusy())) {
                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d", newmotorFLTarget, newmotorFRTarget);
                telemetry.update();
            }
        }



        // FUNCTION TO STRAFE LEFT
        public void strafeLeft(double inches) {
            int newmotorFLTarget;
            int newmotorFRTarget;
            int newmotorBLTarget;
            int newmotorBRTarget;

            // Determine new target position, and pass to motor controller
            newmotorFLTarget = robot.motorFL.getCurrentPosition() + (int) (inches * robot.COUNTS_PER_INCH);
            newmotorFRTarget = robot.motorFR.getCurrentPosition() - (int) (inches * robot.COUNTS_PER_INCH);
            newmotorBLTarget = robot.motorBL.getCurrentPosition() - (int) (inches * robot.COUNTS_PER_INCH);
            newmotorBRTarget = robot.motorBR.getCurrentPosition() + (int) (inches * robot.COUNTS_PER_INCH);

            robot.motorFL.setTargetPosition(newmotorFLTarget);
            robot.motorFR.setTargetPosition(newmotorFRTarget);
            robot.motorBL.setTargetPosition(newmotorBLTarget);
            robot.motorBR.setTargetPosition(newmotorBRTarget);

            // Turn On RUN_TO_POSITION
            // robot moves to set position
            robot.motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.motorFL.setPower(Math.abs(robot.DRIVE_SPEED));
            robot.motorFR.setPower(Math.abs(robot.DRIVE_SPEED));
            robot.motorBL.setPower(Math.abs(robot.DRIVE_SPEED));
            robot.motorBR.setPower(Math.abs(robot.DRIVE_SPEED));
            runtime.reset();
            while (opModeIsActive() && (robot.motorFL.isBusy() || robot.motorFR.isBusy() || robot.motorBL.isBusy() || robot.motorBR.isBusy())) {
                // Display it for the driver.
                telemetry.addData("Strafing left", "Running to %7d :%7d", newmotorFLTarget, newmotorFRTarget);
                telemetry.update();
            }
        }




        // FUNCTION TO STRAFE RIGHT
        public void strafeRight(double inches) {
            int newmotorFLTarget;
            int newmotorFRTarget;
            int newmotorBLTarget;
            int newmotorBRTarget;

            // Determine new target position, and pass to motor controller
            newmotorFLTarget = robot.motorFL.getCurrentPosition() - (int) (inches * robot.COUNTS_PER_INCH);
            newmotorFRTarget = robot.motorFR.getCurrentPosition() + (int) (inches * robot.COUNTS_PER_INCH);
            newmotorBLTarget = robot.motorBL.getCurrentPosition() + (int) (inches * robot.COUNTS_PER_INCH);
            newmotorBRTarget = robot.motorBR.getCurrentPosition() - (int) (inches * robot.COUNTS_PER_INCH);

            robot.motorFL.setTargetPosition(newmotorFLTarget);
            robot.motorFR.setTargetPosition(newmotorFRTarget);
            robot.motorBL.setTargetPosition(newmotorBLTarget);
            robot.motorBR.setTargetPosition(newmotorBRTarget);

            // Turn On RUN_TO_POSITION
            // robot moves to set position
            robot.motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.motorFL.setPower(Math.abs(robot.DRIVE_SPEED));
            robot.motorFR.setPower(Math.abs(robot.DRIVE_SPEED));
            robot.motorBL.setPower(Math.abs(robot.DRIVE_SPEED));
            robot.motorBR.setPower(Math.abs(robot.DRIVE_SPEED));
            runtime.reset();
            while (opModeIsActive() && (robot.motorFL.isBusy() || robot.motorFR.isBusy() || robot.motorBL.isBusy() || robot.motorBR.isBusy())) {
                // Display it for the driver.
                telemetry.addData("Strafing right", "Running to %7d :%7d", newmotorFLTarget, newmotorFRTarget);
                telemetry.update();
            }
        }





        // FUNCTION TO MOVE BACKWARD
        public void moveBackward(double inches) {
            int newmotorFLTarget;
            int newmotorFRTarget;
            int newmotorBLTarget;
            int newmotorBRTarget;

            // Determine new target position, and pass to motor controller
            newmotorFLTarget = robot.motorFL.getCurrentPosition() + (int) (inches * robot.COUNTS_PER_INCH);
            newmotorFRTarget = robot.motorFR.getCurrentPosition() + (int) (inches * robot.COUNTS_PER_INCH);
            newmotorBLTarget = robot.motorBL.getCurrentPosition() + (int) (inches * robot.COUNTS_PER_INCH);
            newmotorBRTarget = robot.motorBR.getCurrentPosition() + (int) (inches * robot.COUNTS_PER_INCH);

            robot.motorFL.setTargetPosition(newmotorFLTarget);
            robot.motorFR.setTargetPosition(newmotorFRTarget);
            robot.motorBL.setTargetPosition(newmotorBLTarget);
            robot.motorBR.setTargetPosition(newmotorBRTarget);

            // Turn On RUN_TO_POSITION
            // robot moves to set position
            robot.motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.motorFL.setPower(Math.abs(robot.DRIVE_SPEED));
            robot.motorFR.setPower(Math.abs(robot.DRIVE_SPEED));
            robot.motorBL.setPower(Math.abs(robot.DRIVE_SPEED));
            robot.motorBR.setPower(Math.abs(robot.DRIVE_SPEED));
            runtime.reset();
            while (opModeIsActive() && (robot.motorFL.isBusy() || robot.motorFR.isBusy() || robot.motorBL.isBusy() || robot.motorBR.isBusy())) {
                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d", newmotorFLTarget, newmotorFRTarget);
                telemetry.update();
            }
        }


//hi
        // FUNCTION TO MOVE FORWARD
        public void moveForward(double inches) {
            int newmotorFLTarget;
            int newmotorFRTarget;
            int newmotorBLTarget;
            int newmotorBRTarget;

            // Determine new target position, and pass to motor controller
            newmotorFLTarget = robot.motorFL.getCurrentPosition() - (int) (inches * robot.COUNTS_PER_INCH);
            newmotorFRTarget = robot.motorFR.getCurrentPosition() - (int) (inches * robot.COUNTS_PER_INCH);
            newmotorBLTarget = robot.motorBL.getCurrentPosition() - (int) (inches * robot.COUNTS_PER_INCH);
            newmotorBRTarget = robot.motorBR.getCurrentPosition() - (int) (inches * robot.COUNTS_PER_INCH);
            robot.motorFL.setTargetPosition(newmotorFLTarget);
            robot.motorFR.setTargetPosition(newmotorFRTarget);
            robot.motorBL.setTargetPosition(newmotorBLTarget);
            robot.motorBR.setTargetPosition(newmotorBRTarget);

            // Turn On RUN_TO_POSITION
            // robot moves to set position
            robot.motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.motorFL.setPower(Math.abs(robot.DRIVE_SPEED));
            robot.motorFR.setPower(Math.abs(robot.DRIVE_SPEED));
            robot.motorBL.setPower(Math.abs(robot.DRIVE_SPEED));
            robot.motorBR.setPower(Math.abs(robot.DRIVE_SPEED));
            runtime.reset();
            while (opModeIsActive() && (robot.motorFL.isBusy() || robot.motorFR.isBusy() || robot.motorBL.isBusy() || robot.motorBR.isBusy())) {
                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d", newmotorFLTarget, newmotorFRTarget);
                telemetry.update();
            }
        }



        // ENCODER FUNCTIONS
        public void resetEncoder()
        {
            robot.motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.motorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.motorBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.elbowMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
        public void startEncoderMode()
        {
            //Set Encoder Mode
            robot.motorFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.elbowMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }



//        //RAISE ARM FUNCTION
//        public void raise(double count) {
//
//            int newElbowMotorTarget;
//
//            // Determine new target position, and pass to motor controller
//            newElbowMotorTarget = robot.elbowMotor.getCurrentPosition() + (int) (count);
//            robot.elbowMotor.setTargetPosition(newElbowMotorTarget);
//
//            // Turn On RUN_TO_POSITION
//            robot.elbowMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//            robot.elbowMotor.setPower(Math.abs(robot.DRIVE_SPEED));
//
//        }
//
//
//
//        //LOWER ARM FUNCTION
//        public void lower(double count) {
//
//            int newElbowMotorTarget;
//
//            // Determine new target position, and pass to motor controller
//            newElbowMotorTarget = robot.elbowMotor.getCurrentPosition() - (int) (count);
//            robot.elbowMotor.setTargetPosition(newElbowMotorTarget);
//
//            // Turn On RUN_TO_POSITION
//            robot.elbowMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//            robot.elbowMotor.setPower(Math.abs(robot.DRIVE_SPEED));
//
//        }
//
//
//
//        public void placeFreight(){
//            //move motor down
//            robot.elbowMotor.setPower(0.2);
//            runtime.reset();
//            while (runtime.seconds() < 0.6){
//            }
//
//            //unclamp servo
//            robot.elbowMotor.setPower(0);
//            robot.freightSnatcher1.setPosition(0.6);
//
//            //wait
//            runtime.reset();
//            while (runtime.seconds() < 1){
//            }
//
//            //move arm back up
//            robot.elbowMotor.setPower(-0.2);
//            runtime.reset();
//            while (runtime.seconds() < 0.5){
//            }
//
//            robot.elbowMotor.setPower(0);
//        }
}