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

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.CalendarContract;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import de.baumann.hhsmoodle.HHS_Browser;
import de.baumann.hhsmoodle.HHS_Note;
import de.baumann.hhsmoodle.R;
import de.baumann.hhsmoodle.helper.CustomListAdapter;
import de.baumann.hhsmoodle.helper.helpers;

public class FragmentInfo extends Fragment {

    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final String[] itemTITLE ={
                getString(R.string.text_tit_1),
                getString(R.string.text_tit_2),
                getString(R.string.text_tit_8),
                getString(R.string.text_tit_3),
                getString(R.string.text_tit_4),
                getString(R.string.text_tit_5),
                getString(R.string.text_tit_6),
                getString(R.string.text_tit_7),
        };

        final String[] itemURL ={
                "https://moodle.huebsch.ka.schule-bw.de/moodle/my/",
                "https://moodle.huebsch.ka.schule-bw.de/moodle/user/profile.php",
                "https://moodle.huebsch.ka.schule-bw.de/moodle/calendar/view.php",
                "https://moodle.huebsch.ka.schule-bw.de/moodle/grade/report/overview/index.php",
                "https://moodle.huebsch.ka.schule-bw.de/moodle/message/index.php",
                "https://moodle.huebsch.ka.schule-bw.de/moodle/user/preferences.php",
                "http://www.huebsch-ka.de/",
                "https://startpage.com/",
        };

            final String[] itemDES ={
                getString(R.string.text_des_1),
                getString(R.string.text_des_2),
                getString(R.string.text_des_8),
                getString(R.string.text_des_3),
                getString(R.string.text_des_4),
                getString(R.string.text_des_5),
                getString(R.string.text_des_6),
                getString(R.string.text_des_7),
        };

        Integer[] imgid={
                R.drawable.ic_view_dashboard_grey600_48dp,
                R.drawable.ic_face_profile_grey600_48dp,
                R.drawable.ic_calendar_grey600_48dp,
                R.drawable.ic_chart_areaspline_grey600_48dp,
                R.drawable.ic_bell_grey600_48dp,
                R.drawable.ic_settings_grey600_48dp,
                R.drawable.ic_web_grey600_48dp,
                R.drawable.ic_magnify_grey600_48dp,
        };

        View rootView = inflater.inflate(R.layout.fragment_screen_main, container, false);

        ImageView imgHeader = (ImageView) rootView.findViewById(R.id.imageView_header);
        if(imgHeader != null) {
            TypedArray images = getResources().obtainTypedArray(R.array.splash_images);
            int choice = (int) (Math.random() * images.length());
            imgHeader.setImageResource(images.getResourceId(choice, R.drawable.splash1));
            images.recycle();
        }

        setHasOptionsMenu(true);

        CustomListAdapter adapter=new CustomListAdapter(getActivity(), itemTITLE, itemURL, itemDES, imgid);
        listView = (ListView)rootView.findViewById(R.id.bookmarks);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String Selecteditem= itemURL[+position];
                helpers.isOpened(getActivity());
                helpers.switchToActivity(getActivity(), HHS_Browser.class, Selecteditem, false);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final String title = itemTITLE[+position];
                final String url = itemURL[+position];

                final CharSequence[] options = {
                        getString(R.string.bookmark_edit_fav),
                        getString(R.string.bookmark_createNote),
                        getString(R.string.bookmark_createShortcut),
                        getString(R.string.bookmark_createEvent)};
                new AlertDialog.Builder(getActivity())
                        .setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int item) {

                                if (options[item].equals (getString(R.string.bookmark_edit_fav))) {
                                    final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                                    sharedPref.edit()
                                            .putString("favoriteURL", url)
                                            .putString("favoriteTitle", title)
                                            .apply();
                                    Snackbar.make(listView, R.string.bookmark_setFav, Snackbar.LENGTH_LONG).show();
                                }

                                if (options[item].equals (getString(R.string.bookmark_createEvent))) {
                                    Intent calIntent = new Intent(Intent.ACTION_INSERT);
                                    calIntent.setType("vnd.android.cursor.item/event");
                                    calIntent.putExtra(CalendarContract.Events.TITLE, title);
                                    startActivity(calIntent);
                                }

                                if (options[item].equals (getString(R.string.bookmark_createNote))) {
                                    SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                                    sharedPref.edit()
                                            .putString("handleTextTitle", title)
                                            .putString("handleTextText", url)
                                            .apply();
                                    helpers.isOpened(getActivity());
                                    helpers.switchToActivity(getActivity(), HHS_Note.class, "", false);
                                }

                                if (options[item].equals (getString(R.string.bookmark_createShortcut))) {
                                    Intent i = new Intent();
                                    i.setAction(Intent.ACTION_VIEW);
                                    i.setData(Uri.parse(url));

                                    Intent shortcut = new Intent();
                                    shortcut.putExtra("android.intent.extra.shortcut.INTENT", i);
                                    shortcut.putExtra("android.intent.extra.shortcut.NAME", "THE NAME OF SHORTCUT TO BE SHOWN");
                                    shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
                                    shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource.fromContext(getActivity().getApplicationContext(), R.mipmap.ic_launcher));
                                    shortcut.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
                                    getActivity().sendBroadcast(shortcut);
                                    Snackbar.make(listView, R.string.toast_shortcut, Snackbar.LENGTH_LONG).show();
                                }

                            }
                        }).show();

                return true;
            }
        });

        return rootView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_help:
                final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity())
                        .setTitle(R.string.title_info)
                        .setMessage(helpers.textSpannable(getString(R.string.helpInfo_text)))
                        .setPositiveButton(getString(R.string.toast_yes), null);
                dialog.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}