package org.bulldog.examples;

import io.silverspoon.bulldog.beagleboneblack.BBBNames;
import io.silverspoon.bulldog.core.gpio.DigitalInput;
import io.silverspoon.bulldog.core.platform.Board;
import io.silverspoon.bulldog.core.platform.Platform;
import io.silverspoon.bulldog.core.util.BulldogUtil;
import io.silverspoon.bulldog.devices.switches.IncrementalRotaryEncoder;
import io.silverspoon.bulldog.devices.switches.RotaryEncoderListener;

import java.io.IOException;

public class RotaryEncoderExample {

   public static void main(String[] args) throws IOException {
      // Create the board
      Board board = Platform.createBoard();

      // Set up two interrupts for the clockwise resp. counter clockwise signals on the encoder
      DigitalInput clockwiseSignal = board.getPin(BBBNames.P8_12).as(DigitalInput.class);
      DigitalInput counterClockwiseSignal = board.getPin(BBBNames.P8_13).as(DigitalInput.class);

      // Create the encoder with these digital inputs
      IncrementalRotaryEncoder encoder = new IncrementalRotaryEncoder(clockwiseSignal, counterClockwiseSignal);

      // Add a listener to print the value
      encoder.addListener(new RotaryEncoderListener() {
         public void valueChanged(Integer oldValue, Integer newValue) {
            System.out.println(newValue);
         }

         public void turnedClockwise() {
            System.out.println("cw turn");
         }

         public void turnedCounterclockwise() {
            System.out.println("ccw turn");
         }
      });

      // The program aborts when the value is smaller than -100 or greater 100
      while (encoder.getValue() < 100 && encoder.getValue() > -100) {
         BulldogUtil.sleepMs(50);
      }
   }
}
