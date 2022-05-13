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

import upm_dfrorp.DFRORP;

public class DFRORP_Example
{
    public static void main(String[] args) throws InterruptedException
    {
// ! [Interesting]

        // Instantiate a DFRobot ORP sensor on analog pin A0 with an
        // analog reference voltage of 5.0.
        DFRORP sensor = new DFRORP(0, 5.0f);

        // To calibrate:
        //
        // Disconnect the sensor probe (but leave the sensor interface
        // board connected).  Then run one of the examples while
        // holding down the 'calibrate' button on the device.  Read
        // the ORP value reported (it should be fairly small).
        //
        // This value is what you should supply to
        // setCalibrationOffset().  Then reconnect the probe to the
        // interface board and you should be ready to go.
        //
        // DO NOT press the calibrate button on the interface board
        // while the probe is attached or you can permanently damage
        // the probe.
        sensor.setCalibrationOffset(0.97f);

        // Every second, update and print values
        while (true)
            {
                sensor.update();

                System.out.println("ORP: " +
                                   sensor.getORP()
                                   + " mV");

                System.out.println();
                Thread.sleep(1000);
            }

// ! [Interesting]
    }
}
