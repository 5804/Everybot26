// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.sim.TalonFXSimState;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


import static frc.robot.Constants.FuelConstants.*;

import java.io.ObjectInputFilter.Config;

public class CANFuelSubsystem extends SubsystemBase {
  private final TalonFX LeftIntakeLauncher;
  private final TalonFX RightIntakeLauncher;
  //private final TalonFX Indexer;
  private final WPI_TalonSRX Indexer;

  public CANFuelSubsystem() {
    LeftIntakeLauncher = new TalonFX(LEFT_INTAKE_LAUNCHER_MOTOR_ID);
    RightIntakeLauncher = new TalonFX(RIGHT_INTAKE_LAUNCHER_MOTOR_ID);
    //TalonFX indexer = new TalonFX(INDEXER_MOTOR_ID);
    Indexer = new WPI_TalonSRX(INDEXER_MOTOR_ID);
    
    TalonFXConfiguration configs;
    TalonFXConfiguration supplyCurrentLimitConfiguration = new TalonFXConfiguration();
    //TalonFXConfiguration indexerConfig = new TalonFXConfiguration();
    TalonFXConfiguration feederConfig = new TalonFXConfiguration();
    TalonFXConfiguration launcherConfig = new TalonFXConfiguration();
    CurrentLimitsConfigs motorCurrentLimitConfig = new CurrentLimitsConfigs();
    
    motorCurrentLimitConfig.SupplyCurrentLimit = INDEXER_MOTOR_CURRENT_LIMIT;
    motorCurrentLimitConfig.SupplyCurrentLimitEnable = true;

    //indexerConfig.withCurrentLimits(motorCurrentLimitConfig);
    feederConfig.withCurrentLimits(motorCurrentLimitConfig);
    launcherConfig.withCurrentLimits(motorCurrentLimitConfig);
    //launcherConfig.inverted(true);
    //launcherConfig.

    // put default values for various fuel operations onto the dashboard
    // all commands using this subsystem pull values from the dashbaord to allow
    // you to tune the values easily, and then replace the values in Constants.java
    // with your new values. For more information, see the Software Guide.
    SmartDashboard.putNumber("Intaking feeder roller value", INDEXER_INTAKING_PERCENT);
    SmartDashboard.putNumber("Intaking intake roller value", INTAKE_INTAKING_PERCENT);
    SmartDashboard.putNumber("Launching feeder roller value", INDEXER_LAUNCHING_PERCENT);
    SmartDashboard.putNumber("Launching launcher roller value", LAUNCHING_LAUNCHER_PERCENT);
   // SmartDashboard.putNumber("Eject value", INTAKE_EJECT_PERCENT);
    //SmartDashboard.putNumber("Spin-up feeder roller value", SPIN_UP_FEEDER_VOLTAGE);
  }

  // A method to set the voltage of the intake roller
  public void setIntakeLauncherRoller(double power) {
    LeftIntakeLauncher.set(power);
    RightIntakeLauncher.set(power); // positive for shooting
  }

  // A method to set the voltage of the intake roller
  public void setFeederRoller(double power) {
    Indexer.set(power); // positive for shooting
  }

  // A method to stop the rollers
  public void stop() {
    Indexer.set(0);
    LeftIntakeLauncher.set(0);
    RightIntakeLauncher.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
