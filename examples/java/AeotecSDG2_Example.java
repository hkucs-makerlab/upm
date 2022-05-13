/*
 * Author: Jon Trulson <jtrulson@ics.com>
 * Copyright (c) 2016 Intel Corporation.
 *
 * This program and the accompanying materials are made available under the
 * terms of the The MIT License which is available at
 * https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: MIT
 */

import upm_ozw.AeotecSDG2;

public class AeotecSDG2_Example
{
    private static String defaultDev = "/dev/ttyACM0";

    public static void main(String[] args) throws InterruptedException
    {
// ! [Interesting]

        if (args.length > 0)
            defaultDev = args[0];
        System.out.println("Using device " + defaultDev);

        // Instantiate an Aeotec Smart Dimmer Gen2 instance, on device node
        // 9.  You will almost certainly need to change this to reflect your
        // own network.  Use the ozwdump example to see what nodes are
        // available.
        AeotecSDG2 sensor = new AeotecSDG2(9);

        // The first thing to do is create options, then lock them when done.
        sensor.optionsCreate();
        sensor.optionsLock();

        System.out.println("Initializing, this may take awhile depending "
                           + "on your ZWave network");

        sensor.init(defaultDev);
        System.out.println("Initialization complete");

        // turn light on
        System.out.println("Turning switch on, then sleeping for 5 secs");
        sensor.on();
        Thread.sleep(5000);

        System.out.println("Querying data...");

        boolean dim = false;
        while (true)
            {
                // put on a light show...
                if (dim)
                    sensor.setLevel(25);
                else
                    sensor.on();

                dim = !dim;

                sensor.update();

                System.out.println("Switch status: "
                                   + sensor.isOn());

                System.out.println("Volts: "
                                   + sensor.getVolts()
                                   + " volts");

                System.out.println("Energy Consumption: "
                                   + sensor.getEnergy()
                                   + " kWh");

                System.out.println("Watts: "
                                   + sensor.getWatts());

                System.out.println("Current: "
                                   + sensor.getCurrent()
                                   + " amps");

                System.out.println();

                Thread.sleep(5000);
            }

// ! [Interesting]
    }
}
