package org.bulldog.examples;

import io.silverspoon.bulldog.beagleboneblack.BBBNames;
import io.silverspoon.bulldog.core.gpio.DigitalIO;
import io.silverspoon.bulldog.core.io.PinIOGroup;
import io.silverspoon.bulldog.core.platform.Board;
import io.silverspoon.bulldog.core.platform.Platform;
import io.silverspoon.bulldog.core.util.BulldogUtil;

import java.io.IOException;

public class PinIOGroupExample {

   public static void main(String[] args) throws IOException {
      // Grab the platform the application is running on
      Board board = Platform.createBoard();

      PinIOGroup ioGroup = new PinIOGroup(board.getPin(BBBNames.P8_15).as(DigitalIO.class), 100, 
            board.getPin(BBBNames.P8_11).as(DigitalIO.class), 
            board.getPin(BBBNames.P8_12).as(DigitalIO.class), 
            board.getPin(BBBNames.P8_13).as(DigitalIO.class), 
            board.getPin(BBBNames.P8_14).as(DigitalIO.class));

      while (true) {
         for (int i = 0; i < 16; i++) {
            ioGroup.writeByte(i);
         }
         BulldogUtil.sleepMs(100);
      }
   }
}
