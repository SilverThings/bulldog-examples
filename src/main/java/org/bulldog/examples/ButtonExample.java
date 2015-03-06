package org.bulldog.examples;

import io.silverspoon.bulldog.beagleboneblack.BBBNames;
import io.silverspoon.bulldog.core.Signal;
import io.silverspoon.bulldog.core.gpio.DigitalInput;
import io.silverspoon.bulldog.core.platform.Board;
import io.silverspoon.bulldog.core.platform.Platform;
import io.silverspoon.bulldog.core.util.BulldogUtil;
import io.silverspoon.bulldog.devices.switches.Button;
import io.silverspoon.bulldog.devices.switches.ButtonListener;

import java.io.IOException;

public class ButtonExample {

   public static void main(String[] args) throws IOException {
      // Grab the platform the application is running on
      Board board = Platform.createBoard();

      // Set up a digital input
      DigitalInput buttonSignal = board.getPin(BBBNames.P8_12).as(DigitalInput.class);

      // Create the button with this DigitalInput
      Button button = new Button(buttonSignal, Signal.Low);

      // Add a button listener
      button.addListener(new ButtonListener() {

         public void buttonPressed() {
            System.out.println("PRESSED");
         }

         public void buttonReleased() {
            System.out.println("RELEASED");
         }
      });

      while (true) {
         BulldogUtil.sleepMs(50);
      }
   }
}
