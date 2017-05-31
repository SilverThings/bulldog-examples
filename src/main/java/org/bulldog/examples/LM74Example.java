package org.bulldog.examples;

import java.io.IOException;
import java.util.List;

import io.silverspoon.bulldog.core.gpio.DigitalOutput;
import io.silverspoon.bulldog.core.io.bus.spi.SpiBus;
import io.silverspoon.bulldog.core.io.bus.spi.SpiConnection;
import io.silverspoon.bulldog.core.platform.Board;
import io.silverspoon.bulldog.core.platform.Platform;
import io.silverspoon.bulldog.devices.sensors.LM74TemperatureSensor;
import io.silverspoon.bulldog.raspberrypi.RaspiNames;

/**
 * Example for reading temperature from temp sensor TI LM74 (SPI).
 *
 * @author <a href="mailto:mat.per.vt@gmail.com">Matej Perejda</a>
 */
public class LM74Example {

   private static List<SpiBus> buses;

   public static void main(String[] args) throws IOException {

      // Get your platform
      final Board board = Platform.createBoard();

      // Returns available SPI buses {SPI_0_0, SPI_0_1} from board
      buses = board.getSpiBuses();

      // Retrieve the SPIBus object
      SpiBus bus = buses.get(0);

      // Output data stream via pin P1_19 = GPIO10 (SPI_MOSI)
      DigitalOutput masterOutputSlaveInput = board.getPin(RaspiNames.P1_19).as(DigitalOutput.class);

      // SPI connection initialization
      SpiConnection connection = bus.createSpiConnection(masterOutputSlaveInput);

      // Create object representing Texas Instruments LM74 sensor
      LM74TemperatureSensor tempSensor = new LM74TemperatureSensor(connection, masterOutputSlaveInput);

      // Open SPI bus if it's closed
      bus.open();

      // Print temperature to console
      System.out.println("Temperature: " + tempSensor.readTemperature());

      bus.close();
   }
}
