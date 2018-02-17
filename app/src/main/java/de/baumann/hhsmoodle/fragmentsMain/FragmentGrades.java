/*
    This file is part of the HHS Moodle WebApp.

    HHS Moodle WebApp is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    HHS Moodle WebApp is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with the Diaspora Native WebApp.

    If not, see <http://www.gnu.org/licenses/>.
 */

package de.baumann.hhsmoodle.fragmentsMain;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

import de.baumann.hhsmoodle.R;
import de.baumann.hhsmoodle.helper.helper_main;

public class FragmentGrades extends Fragment {

    private EditText maxPointsText;
    private TextView text_100_points;

    private TextView text_99_points;
    private TextView text_98_points;
    private TextView text_97_points;
    private TextView text_96_points;
    private TextView text_95_points;
    private TextView text_94_points;
    private TextView text_93_points;
    private TextView text_92_points;
    private TextView text_91_points;
    private TextView text_90_points;

    private TextView text_89_points;
    private TextView text_88_points;
    private TextView text_87_points;
    private TextView text_86_points;
    private TextView text_85_points;
    private TextView text_84_points;
    private TextView text_83_points;
    private TextView text_82_points;
    private TextView text_81_points;
    private TextView text_80_points;

    private TextView text_79_points;
    private TextView text_78_points;
    private TextView text_77_points;
    private TextView text_76_points;
    private TextView text_75_points;
    private TextView text_74_points;
    private TextView text_73_points;
    private TextView text_72_points;
    private TextView text_71_points;
    private TextView text_70_points;

    private TextView text_69_points;
    private TextView text_68_points;
    private TextView text_67_points;
    private TextView text_66_points;
    private TextView text_65_points;
    private TextView text_64_points;
    private TextView text_63_points;
    private TextView text_62_points;
    private TextView text_61_points;
    private TextView text_60_points;

    private TextView text_59_points;
    private TextView text_58_points;
    private TextView text_57_points;
    private TextView text_56_points;
    private TextView text_55_points;
    private TextView text_54_points;
    private TextView text_53_points;
    private TextView text_52_points;
    private TextView text_51_points;
    private TextView text_50_points;

    private TextView text_49_points;
    private TextView text_48_points;
    private TextView text_47_points;
    private TextView text_46_points;
    private TextView text_45_points;
    private TextView text_44_points;
    private TextView text_43_points;
    private TextView text_42_points;
    private TextView text_41_points;
    private TextView text_40_points;

    private TextView text_39_points;
    private TextView text_38_points;
    private TextView text_37_points;
    private TextView text_36_points;
    private TextView text_35_points;
    private TextView text_34_points;
    private TextView text_33_points;
    private TextView text_32_points;
    private TextView text_31_points;
    private TextView text_30_points;

    private TextView text_29_points;
    private TextView text_28_points;
    private TextView text_27_points;
    private TextView text_26_points;
    private TextView text_25_points;
    private TextView text_24_points;
    private TextView text_23_points;
    private TextView text_22_points;
    private TextView text_21_points;
    private TextView text_20_points;

    private TextView text_19_points;
    private TextView text_18_points;
    private TextView text_17_points;
    private TextView text_16_points;
    private TextView text_15_points;
    private TextView text_14_points;
    private TextView text_13_points;
    private TextView text_12_points;
    private TextView text_11_points;
    private TextView text_10_points;

    private TextView text_09_points;
    private TextView text_08_points;
    private TextView text_07_points;
    private TextView text_06_points;
    private TextView text_05_points;
    private TextView text_04_points;
    private TextView text_03_points;
    private TextView text_02_points;
    private TextView text_01_points;
    private TextView text_00_points;

    private double maxPoints;
    private String maxPointsString;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_screen_grade, container, false);

        maxPointsText = rootView.findViewById(R.id.maxPoints);
        maxPointsText.setText(R.string.grade_100);

        text_100_points = rootView.findViewById(R.id.text_100_points);
        text_99_points = rootView.findViewById(R.id.text_99_points);
        text_98_points = rootView.findViewById(R.id.text_98_points);
        text_97_points = rootView.findViewById(R.id.text_97_points);
        text_96_points = rootView.findViewById(R.id.text_96_points);
        text_95_points = rootView.findViewById(R.id.text_95_points);
        text_94_points = rootView.findViewById(R.id.text_94_points);
        text_93_points = rootView.findViewById(R.id.text_93_points);
        text_92_points = rootView.findViewById(R.id.text_92_points);
        text_91_points = rootView.findViewById(R.id.text_91_points);
        text_90_points = rootView.findViewById(R.id.text_90_points);

        text_89_points = rootView.findViewById(R.id.text_89_points);
        text_88_points = rootView.findViewById(R.id.text_88_points);
        text_87_points = rootView.findViewById(R.id.text_87_points);
        text_86_points = rootView.findViewById(R.id.text_86_points);
        text_85_points = rootView.findViewById(R.id.text_85_points);
        text_84_points = rootView.findViewById(R.id.text_84_points);
        text_83_points = rootView.findViewById(R.id.text_83_points);
        text_82_points = rootView.findViewById(R.id.text_82_points);
        text_81_points = rootView.findViewById(R.id.text_81_points);
        text_80_points = rootView.findViewById(R.id.text_80_points);

        text_79_points = rootView.findViewById(R.id.text_79_points);
        text_78_points = rootView.findViewById(R.id.text_78_points);
        text_77_points = rootView.findViewById(R.id.text_77_points);
        text_76_points = rootView.findViewById(R.id.text_76_points);
        text_75_points = rootView.findViewById(R.id.text_75_points);
        text_74_points = rootView.findViewById(R.id.text_74_points);
        text_73_points = rootView.findViewById(R.id.text_73_points);
        text_72_points = rootView.findViewById(R.id.text_72_points);
        text_71_points = rootView.findViewById(R.id.text_71_points);
        text_70_points = rootView.findViewById(R.id.text_70_points);

        text_69_points = rootView.findViewById(R.id.text_69_points);
        text_68_points = rootView.findViewById(R.id.text_68_points);
        text_67_points = rootView.findViewById(R.id.text_67_points);
        text_66_points = rootView.findViewById(R.id.text_66_points);
        text_65_points = rootView.findViewById(R.id.text_65_points);
        text_64_points = rootView.findViewById(R.id.text_64_points);
        text_63_points = rootView.findViewById(R.id.text_63_points);
        text_62_points = rootView.findViewById(R.id.text_62_points);
        text_61_points = rootView.findViewById(R.id.text_61_points);
        text_60_points = rootView.findViewById(R.id.text_60_points);

        text_59_points = rootView.findViewById(R.id.text_59_points);
        text_58_points = rootView.findViewById(R.id.text_58_points);
        text_57_points = rootView.findViewById(R.id.text_57_points);
        text_56_points = rootView.findViewById(R.id.text_56_points);
        text_55_points = rootView.findViewById(R.id.text_55_points);
        text_54_points = rootView.findViewById(R.id.text_54_points);
        text_53_points = rootView.findViewById(R.id.text_53_points);
        text_52_points = rootView.findViewById(R.id.text_52_points);
        text_51_points = rootView.findViewById(R.id.text_51_points);
        text_50_points = rootView.findViewById(R.id.text_50_points);

        text_49_points = rootView.findViewById(R.id.text_49_points);
        text_48_points = rootView.findViewById(R.id.text_48_points);
        text_47_points = rootView.findViewById(R.id.text_47_points);
        text_46_points = rootView.findViewById(R.id.text_46_points);
        text_45_points = rootView.findViewById(R.id.text_45_points);
        text_44_points = rootView.findViewById(R.id.text_44_points);
        text_43_points = rootView.findViewById(R.id.text_43_points);
        text_42_points = rootView.findViewById(R.id.text_42_points);
        text_41_points = rootView.findViewById(R.id.text_41_points);
        text_40_points = rootView.findViewById(R.id.text_40_points);

        text_39_points = rootView.findViewById(R.id.text_39_points);
        text_38_points = rootView.findViewById(R.id.text_38_points);
        text_37_points = rootView.findViewById(R.id.text_37_points);
        text_36_points = rootView.findViewById(R.id.text_36_points);
        text_35_points = rootView.findViewById(R.id.text_35_points);
        text_34_points = rootView.findViewById(R.id.text_34_points);
        text_33_points = rootView.findViewById(R.id.text_33_points);
        text_32_points = rootView.findViewById(R.id.text_32_points);
        text_31_points = rootView.findViewById(R.id.text_31_points);
        text_30_points = rootView.findViewById(R.id.text_30_points);

        text_29_points = rootView.findViewById(R.id.text_29_points);
        text_28_points = rootView.findViewById(R.id.text_28_points);
        text_27_points = rootView.findViewById(R.id.text_27_points);
        text_26_points = rootView.findViewById(R.id.text_26_points);
        text_25_points = rootView.findViewById(R.id.text_25_points);
        text_24_points = rootView.findViewById(R.id.text_24_points);
        text_23_points = rootView.findViewById(R.id.text_23_points);
        text_22_points = rootView.findViewById(R.id.text_22_points);
        text_21_points = rootView.findViewById(R.id.text_21_points);
        text_20_points = rootView.findViewById(R.id.text_20_points);

        text_19_points = rootView.findViewById(R.id.text_19_points);
        text_18_points = rootView.findViewById(R.id.text_18_points);
        text_17_points = rootView.findViewById(R.id.text_17_points);
        text_16_points = rootView.findViewById(R.id.text_16_points);
        text_15_points = rootView.findViewById(R.id.text_15_points);
        text_14_points = rootView.findViewById(R.id.text_14_points);
        text_13_points = rootView.findViewById(R.id.text_13_points);
        text_12_points = rootView.findViewById(R.id.text_12_points);
        text_11_points = rootView.findViewById(R.id.text_11_points);
        text_10_points = rootView.findViewById(R.id.text_10_points);

        text_09_points = rootView.findViewById(R.id.text_09_points);
        text_08_points = rootView.findViewById(R.id.text_08_points);
        text_07_points = rootView.findViewById(R.id.text_07_points);
        text_06_points = rootView.findViewById(R.id.text_06_points);
        text_05_points = rootView.findViewById(R.id.text_05_points);
        text_04_points = rootView.findViewById(R.id.text_04_points);
        text_03_points = rootView.findViewById(R.id.text_03_points);
        text_02_points = rootView.findViewById(R.id.text_02_points);
        text_01_points = rootView.findViewById(R.id.text_01_points);
        text_00_points = rootView.findViewById(R.id.text_00_points);

        maxPointsString = maxPointsText.getText().toString();

        if (maxPointsString.length() > 0) {
            calculate();
        } else {
            Snackbar.make(maxPointsText, R.string.grade_toast, Snackbar.LENGTH_LONG).show();
        }

        FloatingActionButton fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                maxPointsString = maxPointsText.getText().toString();

                if (maxPointsString.length() > 0) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(maxPointsText.getWindowToken(), 0);
                    calculate();
                } else {
                    Snackbar.make(maxPointsText, R.string.grade_toast, Snackbar.LENGTH_LONG).show();
                }
            }
        });
        setHasOptionsMenu(true);
        return rootView;
    }

    private void actualPoint(double percentText, TextView text) {

        double actPointsLong = (percentText/100) * maxPoints;
        String actPointsString = String.format(Locale.GERMANY, "%.1f", actPointsLong);
        text.setText(String.valueOf(String.valueOf(actPointsString)));
    }

    private void calculate () {
        maxPoints = Double.parseDouble(maxPointsText.getText().toString());

        actualPoint(100, text_100_points);
        actualPoint( 99,  text_99_points);
        actualPoint( 98,  text_98_points);
        actualPoint( 97,  text_97_points);
        actualPoint( 96,  text_96_points);
        actualPoint( 95,  text_95_points);
        actualPoint( 94,  text_94_points);
        actualPoint( 93,  text_93_points);
        actualPoint( 92,  text_92_points);
        actualPoint( 91,  text_91_points);
        actualPoint( 90,  text_90_points);

        actualPoint( 89,  text_89_points);
        actualPoint( 88,  text_88_points);
        actualPoint( 87,  text_87_points);
        actualPoint( 86,  text_86_points);
        actualPoint( 85,  text_85_points);
        actualPoint( 84,  text_84_points);
        actualPoint( 83,  text_83_points);
        actualPoint( 82,  text_82_points);
        actualPoint( 81,  text_81_points);
        actualPoint( 80,  text_80_points);

        actualPoint( 79,  text_79_points);
        actualPoint( 78,  text_78_points);
        actualPoint( 77,  text_77_points);
        actualPoint( 76,  text_76_points);
        actualPoint( 75,  text_75_points);
        actualPoint( 74,  text_74_points);
        actualPoint( 73,  text_73_points);
        actualPoint( 72,  text_72_points);
        actualPoint( 71,  text_71_points);
        actualPoint( 70,  text_70_points);

        actualPoint( 69,  text_69_points);
        actualPoint( 68,  text_68_points);
        actualPoint( 67,  text_67_points);
        actualPoint( 66,  text_66_points);
        actualPoint( 65,  text_65_points);
        actualPoint( 64,  text_64_points);
        actualPoint( 63,  text_63_points);
        actualPoint( 62,  text_62_points);
        actualPoint( 61,  text_61_points);
        actualPoint( 60,  text_60_points);

        actualPoint( 59,  text_59_points);
        actualPoint( 58,  text_58_points);
        actualPoint( 57,  text_57_points);
        actualPoint( 56,  text_56_points);
        actualPoint( 55,  text_55_points);
        actualPoint( 54,  text_54_points);
        actualPoint( 53,  text_53_points);
        actualPoint( 52,  text_52_points);
        actualPoint( 51,  text_51_points);
        actualPoint( 50,  text_50_points);

        actualPoint( 49,  text_49_points);
        actualPoint( 48,  text_48_points);
        actualPoint( 47,  text_47_points);
        actualPoint( 46,  text_46_points);
        actualPoint( 45,  text_45_points);
        actualPoint( 44,  text_44_points);
        actualPoint( 43,  text_43_points);
        actualPoint( 42,  text_42_points);
        actualPoint( 41,  text_41_points);
        actualPoint( 40,  text_40_points);

        actualPoint( 39,  text_39_points);
        actualPoint( 38,  text_38_points);
        actualPoint( 37,  text_37_points);
        actualPoint( 36,  text_36_points);
        actualPoint( 35,  text_35_points);
        actualPoint( 34,  text_34_points);
        actualPoint( 33,  text_33_points);
        actualPoint( 32,  text_32_points);
        actualPoint( 31,  text_31_points);
        actualPoint( 30,  text_30_points);

        actualPoint( 29,  text_29_points);
        actualPoint( 28,  text_28_points);
        actualPoint( 27,  text_27_points);
        actualPoint( 26,  text_26_points);
        actualPoint( 25,  text_25_points);
        actualPoint( 24,  text_24_points);
        actualPoint( 23,  text_23_points);
        actualPoint( 22,  text_22_points);
        actualPoint( 21,  text_21_points);
        actualPoint( 20,  text_20_points);

        actualPoint( 19,  text_19_points);
        actualPoint( 18,  text_18_points);
        actualPoint( 17,  text_17_points);
        actualPoint( 16,  text_16_points);
        actualPoint( 15,  text_15_points);
        actualPoint( 14,  text_14_points);
        actualPoint( 13,  text_13_points);
        actualPoint( 12,  text_12_points);
        actualPoint( 11,  text_11_points);
        actualPoint( 10,  text_10_points);

        actualPoint(  9,  text_09_points);
        actualPoint(  8,  text_08_points);
        actualPoint(  7,  text_07_points);
        actualPoint(  6,  text_06_points);
        actualPoint(  5,  text_05_points);
        actualPoint(  4,  text_04_points);
        actualPoint(  3,  text_03_points);
        actualPoint(  2,  text_02_points);
        actualPoint(  1,  text_01_points);
        actualPoint(  0,  text_00_points);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.action_sort).setVisible(false);
        menu.findItem(R.id.action_filter).setVisible(false);
        getActivity().setTitle(R.string.action_grades);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {

            case R.id.action_help:
                helper_main.switchToActivity(getActivity(), FragmentGrades_Help.class, false);
                return true;
        }
        return false;
    }
}