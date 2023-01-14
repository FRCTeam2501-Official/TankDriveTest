// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

/**
 * This is a demo program showing the use of the DifferentialDrive class, specifically it contains
 * the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private DifferentialDrive m_myRobot;

  private CANSparkMax m_leftMotorFront;
  private CANSparkMax m_leftMotorRear;
  private CANSparkMax m_rightMotorFront;
  private CANSparkMax m_rightMotorRear;

  private MotorControllerGroup m_leftGroup;
  private MotorControllerGroup m_rightGroup;

  private XboxController xboxController;

  @Override
  public void robotInit() {
    // Assign each motor a CAN deviceId so it can be identified and a MotorType.
    m_leftMotorFront = new CANSparkMax(6, MotorType.kBrushless);
    m_leftMotorRear = new CANSparkMax(12, MotorType.kBrushless);
    m_rightMotorFront = new CANSparkMax(8, MotorType.kBrushless);
    m_rightMotorRear = new CANSparkMax(15, MotorType.kBrushless);

    // Assign the motors to a group so they move in sync (robot uses 2 NEO motors per side)
    m_leftGroup = new MotorControllerGroup(m_leftMotorFront, m_leftMotorRear);
    m_rightGroup = new MotorControllerGroup(m_rightMotorFront, m_rightMotorRear);

    // Create the differential drive code 
    m_myRobot = new DifferentialDrive(m_leftGroup, m_rightGroup);

    // Invert one side so each side drives the same direction
    m_leftGroup.setInverted(true);

    // Assign the XboxController to USB Port 0 (can be found in driver station)
    xboxController = new XboxController(0);
  }

  @Override
  public void teleopPeriodic() {
    // Declare tank drive system and assign a button for each side. In this example, it's the two joysticks on the Xbox controller on the Y axis. 
    m_myRobot.tankDrive(xboxController.getLeftY(), xboxController.getRightY());
  }
}
