/*
 * Copyright 2016 tbeckett.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.l2fprod.common.demo.tb;

import java.awt.Color;

/**
 *
 * @author tbeckett
 */
public class GenericBean
{
    
    private Color Color;

    private int Height;

    private int Width;

    /**
     * Get the value of Width
     *
     * @return the value of Width
     */
    public int getWidth()
    {
        return Width;
    }

    /**
     * Set the value of Width
     *
     * @param Width new value of Width
     */
    public void setWidth(int Width)
    {
        this.Width = Width;
    }

    /**
     * Get the value of Height
     *
     * @return the value of Height
     */
    public int getHeight()
    {
        return Height;
    }

    /**
     * Set the value of Height
     *
     * @param Height new value of Height
     */
    public void setHeight(int Height)
    {
        this.Height = Height;
    }

    /**
     * Get the value of Color
     *
     * @return the value of Color
     */
    public Color getColor()
    {
        return Color;
    }

    /**
     * Set the value of Color
     *
     * @param Color new value of Color
     */
    public void setColor(Color Color)
    {
        this.Color = Color;
    }

}
