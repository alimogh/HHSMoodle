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

package de.baumann.hhsmoodle.data_courses;

import android.animation.Animator;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import de.baumann.hhsmoodle.R;
import de.baumann.hhsmoodle.activities.Activity_course;
import de.baumann.hhsmoodle.helper.helper_main;
import de.baumann.hhsmoodle.popup.Popup_files;

public class Courses_Fragment extends Fragment {

    //calling variables
    private Courses_DbAdapter db;
    private ListView lv = null;

    private FloatingActionButton fab;
    private LinearLayout fabLayout1;
    private LinearLayout fabLayout2;
    private boolean isFABOpen=false;
    private SharedPreferences sharedPref;

    private ViewPager viewPager;

    private int top;
    private int index;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_screen_notes, container, false);

        ImageView imgHeader = rootView.findViewById(R.id.imageView_header);
        helper_main.setImageHeader(getActivity(), imgHeader);

        PreferenceManager.setDefaultValues(getActivity(), R.xml.user_settings, false);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());

        RelativeLayout filter_layout = rootView.findViewById(R.id.filter_layout);
        filter_layout.setVisibility(View.GONE);
        lv = rootView.findViewById(R.id.listNotes);
        viewPager = getActivity().findViewById(R.id.viewpager);

        fabLayout1= rootView.findViewById(R.id.fabLayout1);
        fabLayout2= rootView.findViewById(R.id.fabLayout2);
        fab = rootView.findViewById(R.id.fab);
        TextView fab2_text = rootView.findViewById(R.id.text_fab2);
        fab2_text.setText(getString(R.string.courseList_fromText));
        FloatingActionButton fab1 = rootView.findViewById(R.id.fab1);
        FloatingActionButton fab2 = rootView.findViewById(R.id.fab2);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFABOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }
            }
        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeFABMenu();
                Courses_helper.newCourse(getActivity(), "", "", "19","", false);
            }
        });

        fab2.setImageResource(R.drawable.file_document_light);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeFABMenu();
                Intent mainIntent = new Intent(getActivity(), Popup_files.class);
                mainIntent.setAction("file_chooseText");
                startActivity(mainIntent);
            }
        });

        //calling Notes_DbAdapter
        db = new Courses_DbAdapter(getActivity());
        db.open();

        setCoursesList();
        setHasOptionsMenu(true);

        return rootView;
    }

    private void showFABMenu(){
        isFABOpen=true;
        fabLayout1.setVisibility(View.VISIBLE);
        fabLayout2.setVisibility(View.VISIBLE);

        fab.animate().rotationBy(180);
        fabLayout1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        fabLayout2.animate().translationY(-getResources().getDimension(R.dimen.standard_100));
    }

    private void closeFABMenu(){
        isFABOpen=false;
        fab.animate().rotationBy(-180);
        fabLayout1.animate().translationY(0);
        fabLayout2.animate().translationY(0).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {}

            @Override
            public void onAnimationEnd(Animator animator) {
                if(!isFABOpen){
                    fabLayout1.setVisibility(View.GONE);
                    fabLayout2.setVisibility(View.GONE);
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {}

            @Override
            public void onAnimationRepeat(Animator animator) {}
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (viewPager.getCurrentItem() == 9) {
            setCoursesList();
        }
    }

    public void doBack() {
        //BackPressed in activity will call this;
        if(isFABOpen){
            closeFABMenu();
        } else {
            helper_main.onClose(getActivity());
        }
    }

    private void isEdited () {
        sharedPref.edit().putString("edit_yes", "true").apply();
        index = lv.getFirstVisiblePosition();
        View v = lv.getChildAt(0);
        top = (v == null) ? 0 : (v.getTop() - lv.getPaddingTop());
    }

    private void setCoursesList() {

        if(isFABOpen){
            closeFABMenu();
        }

        //display data
        final int layoutstyle=R.layout.list_item_notes;
        int[] xml_id = new int[] {
                R.id.textView_title_notes,
                R.id.textView_des_notes,
                R.id.textView_create_notes
        };
        String[] column = new String[] {
                "courses_title",
                "courses_content",
                "courses_creation"
        };
        final Cursor row = db.fetchAllData();
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(), layoutstyle, row, column, xml_id, 0) {
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {

                Cursor row2 = (Cursor) lv.getItemAtPosition(position);
                final String _id = row2.getString(row2.getColumnIndexOrThrow("_id"));
                final String courses_title = row2.getString(row2.getColumnIndexOrThrow("courses_title"));
                final String courses_content = row2.getString(row2.getColumnIndexOrThrow("courses_content"));
                final String courses_icon = row2.getString(row2.getColumnIndexOrThrow("courses_icon"));
                final String courses_attachment = row2.getString(row2.getColumnIndexOrThrow("courses_attachment"));
                final String courses_creation = row2.getString(row2.getColumnIndexOrThrow("courses_creation"));

                View v = super.getView(position, convertView, parent);
                ImageView iv_icon = v.findViewById(R.id.icon_notes);
                helper_main.switchIcon(getActivity(), courses_icon,"courses_icon", iv_icon);

                iv_icon.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        isEdited();
                        final helper_main.Item[] items = {
                                new helper_main.Item(getString(R.string.text_tit_11), R.drawable.ic_school_grey600_48dp),
                                new helper_main.Item(getString(R.string.text_tit_1), R.drawable.ic_view_dashboard_grey600_48dp),
                                new helper_main.Item(getString(R.string.text_tit_2), R.drawable.ic_face_profile_grey600_48dp),
                                new helper_main.Item(getString(R.string.text_tit_8), R.drawable.ic_calendar_grey600_48dp),
                                new helper_main.Item(getString(R.string.text_tit_3), R.drawable.ic_chart_areaspline_grey600_48dp),
                                new helper_main.Item(getString(R.string.text_tit_4), R.drawable.ic_bell_grey600_48dp),
                                new helper_main.Item(getString(R.string.text_tit_5), R.drawable.ic_settings_grey600_48dp),
                                new helper_main.Item(getString(R.string.text_tit_6), R.drawable.ic_web_grey600_48dp),
                                new helper_main.Item(getString(R.string.text_tit_7), R.drawable.ic_magnify_grey600_48dp),
                                new helper_main.Item(getString(R.string.title_notes), R.drawable.ic_pencil_grey600_48dp),
                                new helper_main.Item(getString(R.string.text_tit_9), R.drawable.ic_check_grey600_48dp),
                                new helper_main.Item(getString(R.string.text_tit_10), R.drawable.ic_clock_grey600_48dp),
                                new helper_main.Item(getString(R.string.title_bookmarks), R.drawable.ic_bookmark_grey600_48dp),
                                new helper_main.Item(getString(R.string.subjects_color_red), R.drawable.circle_red),
                                new helper_main.Item(getString(R.string.subjects_color_pink), R.drawable.circle_pink),
                                new helper_main.Item(getString(R.string.subjects_color_purple), R.drawable.circle_purple),
                                new helper_main.Item(getString(R.string.subjects_color_blue), R.drawable.circle_blue),
                                new helper_main.Item(getString(R.string.subjects_color_teal), R.drawable.circle_teal),
                                new helper_main.Item(getString(R.string.subjects_color_green), R.drawable.circle_green),
                                new helper_main.Item(getString(R.string.subjects_color_lime), R.drawable.circle_lime),
                                new helper_main.Item(getString(R.string.subjects_color_yellow), R.drawable.circle_yellow),
                                new helper_main.Item(getString(R.string.subjects_color_orange), R.drawable.circle_orange),
                                new helper_main.Item(getString(R.string.subjects_color_brown), R.drawable.circle_brown),
                                new helper_main.Item(getString(R.string.subjects_color_grey), R.drawable.circle_grey),
                        };

                        ListAdapter adapter = new ArrayAdapter<helper_main.Item>(
                                getActivity(),
                                android.R.layout.select_dialog_item,
                                android.R.id.text1,
                                items){
                            @NonNull
                            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                                //Use super class to create the View
                                View v = super.getView(position, convertView, parent);
                                TextView tv = v.findViewById(android.R.id.text1);
                                tv.setTextSize(18);
                                tv.setCompoundDrawablesWithIntrinsicBounds(items[position].icon, 0, 0, 0);
                                //Add margin between image and text (support various screen densities)
                                int dp5 = (int) (24 * getResources().getDisplayMetrics().density + 0.5f);
                                tv.setCompoundDrawablePadding(dp5);

                                return v;
                            }
                        };

                        new android.app.AlertDialog.Builder(getActivity())
                                .setPositiveButton(R.string.toast_cancel, new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        dialog.cancel();
                                    }
                                })
                                .setAdapter(adapter, new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int item) {
                                        if (item == 0) {
                                            db.update(Integer.parseInt(_id), helper_main.secString(courses_title), helper_main.secString(courses_content), "01", courses_attachment, courses_creation);
                                            setCoursesList();
                                        } else if (item == 1) {
                                            db.update(Integer.parseInt(_id), helper_main.secString(courses_title), helper_main.secString(courses_content), "02", courses_attachment, courses_creation);
                                            setCoursesList();
                                        } else if (item == 2) {
                                            db.update(Integer.parseInt(_id), helper_main.secString(courses_title), helper_main.secString(courses_content), "03", courses_attachment, courses_creation);
                                            setCoursesList();
                                        } else if (item == 3) {
                                            db.update(Integer.parseInt(_id), helper_main.secString(courses_title), helper_main.secString(courses_content), "04", courses_attachment, courses_creation);
                                            setCoursesList();
                                        } else if (item == 4) {
                                            db.update(Integer.parseInt(_id), helper_main.secString(courses_title), helper_main.secString(courses_content), "05", courses_attachment, courses_creation);
                                            setCoursesList();
                                        } else if (item == 5) {
                                            db.update(Integer.parseInt(_id), helper_main.secString(courses_title), helper_main.secString(courses_content), "06", courses_attachment, courses_creation);
                                            setCoursesList();
                                        } else if (item == 6) {
                                            db.update(Integer.parseInt(_id), helper_main.secString(courses_title), helper_main.secString(courses_content), "07", courses_attachment, courses_creation);
                                            setCoursesList();
                                        } else if (item == 7) {
                                            db.update(Integer.parseInt(_id), helper_main.secString(courses_title), helper_main.secString(courses_content), "08", courses_attachment, courses_creation);
                                            setCoursesList();
                                        } else if (item == 8) {
                                            db.update(Integer.parseInt(_id), helper_main.secString(courses_title), helper_main.secString(courses_content), "09", courses_attachment, courses_creation);
                                            setCoursesList();
                                        } else if (item == 9) {
                                            db.update(Integer.parseInt(_id), helper_main.secString(courses_title), helper_main.secString(courses_content), "10", courses_attachment, courses_creation);
                                            setCoursesList();
                                        } else if (item == 10) {
                                            db.update(Integer.parseInt(_id), helper_main.secString(courses_title), helper_main.secString(courses_content), "11", courses_attachment, courses_creation);
                                            setCoursesList();
                                        } else if (item == 11) {
                                            db.update(Integer.parseInt(_id), helper_main.secString(courses_title), helper_main.secString(courses_content), "12", courses_attachment, courses_creation);
                                            setCoursesList();
                                        } else if (item == 12) {
                                            db.update(Integer.parseInt(_id), helper_main.secString(courses_title), helper_main.secString(courses_content), "13", courses_attachment, courses_creation);
                                            setCoursesList();
                                        } else if (item == 13) {
                                            db.update(Integer.parseInt(_id), helper_main.secString(courses_title), helper_main.secString(courses_content), "14", courses_attachment, courses_creation);
                                            setCoursesList();
                                        } else if (item == 14) {
                                            db.update(Integer.parseInt(_id), helper_main.secString(courses_title), helper_main.secString(courses_content), "15", courses_attachment, courses_creation);
                                            setCoursesList();
                                        } else if (item == 15) {
                                            db.update(Integer.parseInt(_id), helper_main.secString(courses_title), helper_main.secString(courses_content), "16", courses_attachment, courses_creation);
                                            setCoursesList();
                                        } else if (item == 16) {
                                            db.update(Integer.parseInt(_id), helper_main.secString(courses_title), helper_main.secString(courses_content), "17", courses_attachment, courses_creation);
                                            setCoursesList();
                                        } else if (item == 17) {
                                            db.update(Integer.parseInt(_id), helper_main.secString(courses_title), helper_main.secString(courses_content), "18", courses_attachment, courses_creation);
                                            setCoursesList();
                                        } else if (item == 18) {
                                            db.update(Integer.parseInt(_id), helper_main.secString(courses_title), helper_main.secString(courses_content), "19", courses_attachment, courses_creation);
                                            setCoursesList();
                                        } else if (item == 19) {
                                            db.update(Integer.parseInt(_id), helper_main.secString(courses_title), helper_main.secString(courses_content), "20", courses_attachment, courses_creation);
                                            setCoursesList();
                                        } else if (item == 20) {
                                            db.update(Integer.parseInt(_id), helper_main.secString(courses_title), helper_main.secString(courses_content), "21", courses_attachment, courses_creation);
                                            setCoursesList();
                                        } else if (item == 21) {
                                            db.update(Integer.parseInt(_id), helper_main.secString(courses_title), helper_main.secString(courses_content), "22", courses_attachment, courses_creation);
                                            setCoursesList();
                                        } else if (item == 22) {
                                            db.update(Integer.parseInt(_id), helper_main.secString(courses_title), helper_main.secString(courses_content), "23", courses_attachment, courses_creation);
                                            setCoursesList();
                                        } else if (item == 23) {
                                            db.update(Integer.parseInt(_id), helper_main.secString(courses_title), helper_main.secString(courses_content), "24", courses_attachment, courses_creation);
                                            setCoursesList();
                                        }
                                    }
                                }).show();
                    }
                });

                return v;
            }
        };

        lv.setAdapter(adapter);
        lv.setSelectionFromTop(index, top);
        //onClick function
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterview, View view, int position, long id) {

                if(isFABOpen){
                    closeFABMenu();
                }

                isEdited();
                Cursor row2 = (Cursor) lv.getItemAtPosition(position);
                final String _id = row2.getString(row2.getColumnIndexOrThrow("_id"));
                final String courses_title = row2.getString(row2.getColumnIndexOrThrow("courses_title"));
                final String courses_content = row2.getString(row2.getColumnIndexOrThrow("courses_content"));
                final String courses_icon = row2.getString(row2.getColumnIndexOrThrow("courses_icon"));
                final String courses_attachment = row2.getString(row2.getColumnIndexOrThrow("courses_attachment"));
                final String courses_creation = row2.getString(row2.getColumnIndexOrThrow("courses_creation"));

                sharedPref.edit().putString("courses_title", courses_title).apply();
                sharedPref.edit().putString("courses_content", courses_content).apply();
                sharedPref.edit().putString("courses_seqno", _id).apply();
                sharedPref.edit().putString("courses_icon", courses_icon).apply();
                sharedPref.edit().putString("courses_create", courses_creation).apply();
                sharedPref.edit().putString("courses_attachment", courses_attachment).apply();

                helper_main.switchToActivity(getActivity(), Activity_course.class, false);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                if(isFABOpen){
                    closeFABMenu();
                }

                isEdited();
                Cursor row2 = (Cursor) lv.getItemAtPosition(position);
                final String _id = row2.getString(row2.getColumnIndexOrThrow("_id"));
                final String courses_title = row2.getString(row2.getColumnIndexOrThrow("courses_title"));
                final String courses_content = row2.getString(row2.getColumnIndexOrThrow("courses_content"));
                final String courses_icon = row2.getString(row2.getColumnIndexOrThrow("courses_icon"));
                final String courses_attachment = row2.getString(row2.getColumnIndexOrThrow("courses_attachment"));
                final String courses_creation = row2.getString(row2.getColumnIndexOrThrow("courses_creation"));

                final CharSequence[] options = {
                        getString(R.string.number_edit_entry),
                        getString(R.string.bookmark_remove_bookmark)};

                new android.app.AlertDialog.Builder(getActivity())
                        .setPositiveButton(R.string.toast_cancel, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.cancel();
                            }
                        })
                        .setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int item) {

                                if (options[item].equals(getString(R.string.number_edit_entry))) {

                                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                    View dialogView = View.inflate(getActivity(), R.layout.dialog_edit_title, null);

                                    final EditText edit_title = dialogView.findViewById(R.id.pass_title);
                                    edit_title.setHint(R.string.bookmark_edit_title);
                                    edit_title.setText(courses_title);

                                    builder.setView(dialogView);
                                    builder.setTitle(R.string.bookmark_edit_title);
                                    builder.setPositiveButton(R.string.toast_yes, new DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface dialog, int whichButton) {

                                            String inputTitle = edit_title.getText().toString().trim();
                                            db.update(Integer.parseInt(_id),helper_main.secString(inputTitle), helper_main.secString(courses_content), courses_icon, courses_attachment, courses_creation);
                                            setCoursesList();
                                        }
                                    });
                                    builder.setNegativeButton(R.string.toast_cancel, new DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            dialog.cancel();
                                        }
                                    });

                                    final AlertDialog dialog2 = builder.create();
                                    // Display the custom alert dialog on interface
                                    dialog2.show();
                                    helper_main.showKeyboard(getActivity(),edit_title);
                                }

                                if (options[item].equals(getString(R.string.bookmark_remove_bookmark))) {

                                    Snackbar snackbar = Snackbar
                                            .make(lv, R.string.note_remove_confirmation, Snackbar.LENGTH_LONG)
                                            .setAction(R.string.toast_yes, new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    db.delete(Integer.parseInt(_id));
                                                    setCoursesList();
                                                }
                                            });
                                    snackbar.show();
                                }
                            }
                        }).show();
                return true;
            }
        });
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.action_filter).setVisible(false);
        menu.findItem(R.id.action_sort).setVisible(false);
        getActivity().setTitle(R.string.courseList_title);
        setCoursesList();
        helper_main.hideKeyboard(getActivity());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_help:
                helper_main.switchToActivity(getActivity(), Courses_Help.class, false);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}