// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants.AutoConstants;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.CANDriveSubsystem;
import frc.robot.subsystems.CANFuelSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class MiddleAuto extends SequentialCommandGroup {
  /** Creates a new ExampleAuto. */
  public MiddleAuto(CANDriveSubsystem driveSubsystem, CANFuelSubsystem ballSubsystem) {
    addCommands(
    new AutoDrive(driveSubsystem, 
                  AutoConstants.AUTO_DRIVE_SPEED, 
                  0)
                .withTimeout(AutoConstants.AUTO_DRIVE_TIME),

    new LaunchSequence(ballSubsystem).withTimeout(AutoConstants.AUTO_SHOOT_TIME));
  }
}
