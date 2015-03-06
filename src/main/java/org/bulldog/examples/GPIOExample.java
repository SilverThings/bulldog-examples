package org.bulldog.examples;

import io.silverspoon.bulldog.beagleboneblack.BBBNames;
import io.silverspoon.bulldog.core.Signal;
import io.silverspoon.bulldog.core.gpio.DigitalIO;
import io.silverspoon.bulldog.core.platform.Board;
import io.silverspoon.bulldog.core.platform.Platform;
import io.silverspoon.bulldog.core.util.BulldogUtil;

import java.io.IOException;

public class GPIOExample {

   public static void main(String[] args) throws IOException {
      // Detect the board we are running on
      Board board = Platform.createBoard();

      // Set up a digital io
      DigitalIO io = board.getPin(BBBNames.P8_12).as(DigitalIO.class);

      // Set a high signal on the output
      io.write(Signal.High);

      // Toggle it
      io.toggle();
      BulldogUtil.sleepMs(500);

      // Set it high
      io.high();
      BulldogUtil.sleepMs(500);

      // Set it low
      io.low();
      BulldogUtil.sleepMs(500);

      // Yet another way of applying a signal
      io.applySignal(Signal.High);
      BulldogUtil.sleepMs(500);

      // Let it blink 3 times
      io.blinkTimes(500, 3);
      BulldogUtil.sleepMs(2000);

      // Let it blink 5 seconds, 10 times a second
      io.startBlinking(100, 5000);

      // while the blinkins blocks the pin, we wait
      while (io.isBlocking()) {
         BulldogUtil.sleepMs(1000);
      }

      // Let it blink 5 times a second indefinitely
      io.startBlinking(200);
      BulldogUtil.sleepMs(1000);
      io.stopBlinking();

      while (io.isBlocking()) {
         BulldogUtil.sleepMs(1000);
      }

      // read the state of the pin:
      Signal readSignal = io.read();
      System.out.println(readSignal);

      while (true) {
         BulldogUtil.sleepMs(50);
      }
   }
}
