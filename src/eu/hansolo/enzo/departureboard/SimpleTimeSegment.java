/*
 * Copyright (c) 2013, Gerrit Grunwald
 * All right reserved
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * The names of its contributors may not be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package eu.hansolo.enzo.departureboard;

import eu.hansolo.enzo.imgsplitflap.SplitFlap;
import eu.hansolo.enzo.imgsplitflap.SplitFlapBuilder;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.HBox;


/**
 * Created by
 * User: hansolo
 * Date: 17.04.13
 * Time: 15:25
 */
public class SimpleTimeSegment extends HBox {
    private SplitFlap      hourLeft;
    private SplitFlap      hourRight;
    private SplitFlap      minLeft;
    private SplitFlap      minRight;
    private String         defaultHours;
    private StringProperty hours;
    private String         defaultMinutes;
    private StringProperty minutes;


    // ******************** Constructors **************************************
    public SimpleTimeSegment() {
        hourLeft  = SplitFlapBuilder.create()
                                    .selection(SplitFlap.TIME_0_TO_5)
                                    .text("0")
                                    .flipTime(100)
                                    .build();
        hourLeft.setPrefSize(35, 60);
        hourRight = SplitFlapBuilder.create()
                                    .selection(SplitFlap.TIME_0_TO_9)
                                    .text("0")
                                    .flipTime(100)
                                    .build();
        hourRight.setPrefSize(35, 60);
        minLeft   = SplitFlapBuilder.create()
                                    .selection(SplitFlap.TIME_0_TO_5)
                                    .text("0")
                                    .flipTime(100)
                                    .build();
        minLeft.setPrefSize(23, 40);
        minRight  = SplitFlapBuilder.create()
                                    .selection(SplitFlap.TIME_0_TO_9)
                                    .text("0")
                                    .flipTime(100)
                                    .build();
        minRight.setPrefSize(23, 40);

        setSpacing(2);
        getChildren().addAll(hourLeft,
                             hourRight,
                             minLeft,
                             minRight);
    }


    // ******************** Methods *******************************************
    public final String getTime() {
        return (hours.get() + ":" + minutes.get());
    }
    public final void setTime(final String HOURS, final String MINUTES) {
        setHours(HOURS);
        setMinutes(MINUTES);
    }

    public final String getHours() {
        return null == hours ? defaultHours : hours.get();
    }
    public final void setHours(final String HOURS) {
        if (null == hours) {
            defaultHours = HOURS;
        } else {
            hours.set(HOURS);
        }

        if (HOURS.isEmpty()) {
            hourLeft.setText(" ");
            hourRight.setText(" ");
        } else {
            if (HOURS.length() > 1) {
                hourLeft.setText(HOURS.substring(0, 1));
                hourRight.setText(HOURS.substring(1, 2));
            } else {
                hourLeft.setText("0");
                hourRight.setText(HOURS.substring(0, 1));
            }
        }
    }
    public final StringProperty hoursProperty() {
        if (null == hours) {
            hours = new SimpleStringProperty(this, "hours", defaultHours);
        }
        return hours;
    }

    public final String getMinutes() {
        return null == minutes ? defaultMinutes : minutes.get();
    }
    public final void setMinutes(final String MINUTES) {
        if (null == minutes) {
            defaultMinutes = MINUTES;
        } else {
            minutes.set(MINUTES);
        }

        if (MINUTES.isEmpty()) {
            minLeft.setText(" ");
            minRight.setText(" ");
        } else {
            if (MINUTES.length() > 1) {
                minLeft.setText(MINUTES.substring(0, 1));
                minRight.setText(MINUTES.substring(1, 2));
            } else {
                minLeft.setText("0");
                minRight.setText(MINUTES.substring(0, 1));
            }
        }
    }
    public final StringProperty minutesProperty() {
        if (null == minutes) {
            minutes = new SimpleStringProperty(this, "minutes", defaultMinutes);
        }
        return minutes;
    }
}