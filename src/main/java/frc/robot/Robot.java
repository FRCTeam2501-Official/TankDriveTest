// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

/**
 * This is a demo program showing the use of the DifferentialDrive class, specifically it contains
 * the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private DifferentialDrive m_myRobot;
  private Joystick m_leftStick;
  private Joystick m_rightStick;

  private CANSparkMax m_leftMotorFront;
  private CANSparkMax m_leftMotorRear;
  private CANSparkMax m_rightMotorFront;
  private CANSparkMax m_rightMotorRear;

  private MotorControllerGroup m_leftGroup;
  private MotorControllerGroup m_rightGroup;

  @Override
  public void robotInit() {
    m_leftMotorFront = new CANSparkMax(1, MotorType.kBrushless);
    m_leftMotorRear = new CANSparkMax(2, MotorType.kBrushless);
    m_rightMotorFront = new CANSparkMax(3, MotorType.kBrushless);
    m_rightMotorRear = new CANSparkMax(4, MotorType.kBrushless);

    m_leftGroup = new MotorControllerGroup(m_leftMotorFront, m_leftMotorRear);
    m_rightGroup = new MotorControllerGroup(m_rightMotorFront, m_rightMotorRear);

    m_myRobot = new DifferentialDrive(m_leftGroup, m_rightGroup);

    m_leftStick = new Joystick(0);
    m_rightStick = new Joystick(1);
  }

  @Override
  public void teleopPeriodic() {
    m_myRobot.tankDrive(m_leftStick.getY(), m_rightStick.getY());
  }
}
