/*
 *     THIS FILE AND PROJECT IS SUPPLIED FOR EDUCATIONAL PURPOSES ONLY.
 *
 *     This program is free software; you can redistribute it
 *     and/or modify it under the terms of the GNU General
 *     Public License as published by the Free Software
 *     Foundation; either version 2 of the License, or (at your
 *     option) any later version.
 *
 *     This program is distributed in the hope that it will be
 *     useful, but WITHOUT ANY WARRANTY; without even the
 *     implied warranty of MERCHANTABILITY or FITNESS FOR A
 *     PARTICULAR PURPOSE. See the GNU General Public License
 *     for more details.
 *
 *     You should have received a copy of the GNU General
 *     Public License along with this program; if not, write to
 *     the Free Software Foundation, Inc., 59 Temple Place,
 */
package ballistickemu.Tools;

/**
 *
 * @author Simon
 */
public class StringTool {
        public static String PadStringLeft(String toPad, String PadChar, int totalLength)
        {
            String ret;
            ret = "";

            for (int i = 0; i < totalLength - toPad.length(); i++)
            {
                ret = ret + PadChar;
            }
            return ret + toPad;
        }

    public static String GetStringBetween(String line, String start, String end)
    {
        return line.substring(line.indexOf(start) + start.length(), line.indexOf(end));
    }

    public static String GetStringBetween(String line, String end)
    {
        return line.substring(0, line.indexOf(end));
    }

}
