package com.actiknow.callsikandar.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actiknow.callsikandar.R;
import com.actiknow.callsikandar.activity.SupportPageActivity;

/**
 * Created by actiknow on 10/27/16.
 */

public class Support_2_Fragment extends Fragment implements View.OnClickListener {
    String status;
    ListView list;
    View v;
    int flag = 0;
    String tvContactUs[] = {"Customer Service Helpline"};
    String tvPrice[] = {"Why are the prices mentioned on Callsikandar as estimated", "What do the prices in the Callsikandar app include"};
    String tvServiceProvider[] = {"How does Callsikandar help me choose the right provider?", "What happens after I request a service",
            "Are all the service providers listed on Callsikandar authorized by the car manufacturer to repair my car?",
            "How do I ensure service providers use only genuine spare parts?"};
    String tvDoorStep[] = {"Doorstep Services"};
    String tvPickup[] = {"Pickup and Drop option"};
    String tvCarClean[] = {"Will the service provider need access to a power source and water?", "Will a waterless/eco-friendly wash scratch my car?",
            "How long will the service take?",
            "How long will a full detail take?", "If I need a car cleaning subscription service, is the possible to book through Callsikandar app?",
            "What if I'm not happy with my service?"};
    String tvWheels[] = {"What wheel and tyre services do Callsikandar provide?", "How do I know when my tyres need replacing?"};
    String tvRegularMaint[] = {"How often should my car have a full service", "What will be included in the full service?",
            "How can I be sure that only the highest quality products are used?", "Can I book an appointment solely changing my headlight bulbs?"};
    String tvBreakdown[] = {"Breakdown Services"};
    String tvServiceRequest[] = {"Other Services", "Can Callsikandar trouble-shoot the problem I'm having with my car ?",
            "Do Callsikandar provide A/C cleaning/flush services? ", "Do Callsikandar provide seasonal summer/winter/monsoon services?"};
    private ArrayAdapter<String> adapter;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        status = getArguments ().getString ("status");
        Log.e ("Status", "" + status);
        v = inflater.inflate (R.layout.fragment_support_2, null);
        //  }
        initView (v);
        initData ();
        initListener ();
        initAdapter (v);
        return v;
    }

    private void initView (View v) {
        list = (ListView) v.findViewById (R.id.list);

    }

    private void initData () {

    }

    private void initListener () {
    }

    private void initAdapter (View v) {
        switch (status) {
            case "1":
                //   String list = tvContactUs;
                adapter = new ArrayAdapter<String> (getActivity (), android.R.layout.simple_dropdown_item_1line, tvContactUs);
                list.setAdapter (adapter);
                list.setOnItemClickListener (new AdapterView.OnItemClickListener () {
                    @Override
                    public void onItemClick (AdapterView<?> parent, View view, int position,
                                             long id) {
                        Intent intent = new Intent (getActivity (), SupportPageActivity.class);
                        intent.putExtra ("FLAG", flag);
                        startActivity (intent);
                    }
                });
                break;
            case "2":
                adapter = new ArrayAdapter<String> (getActivity (), android.R.layout.simple_dropdown_item_1line, tvPrice);
                list.setAdapter (adapter);
                list.setOnItemClickListener (new AdapterView.OnItemClickListener () {
                    @Override
                    public void onItemClick (AdapterView<?> parent, View view, int position,
                                             long id) {
                        switch (position) {
                            case 0:
                                flag = 1;
                                break;
                            case 1:
                                flag = 2;
                                break;
                        }
                        Intent intent = new Intent (getActivity (), SupportPageActivity.class);
                        intent.putExtra ("FLAG", flag);
                        startActivity (intent);
                        // Toast.makeText(getActivity(), String.valueOf(flag), Toast.LENGTH_LONG).show();
                    }
                });

                break;
            case "3":
                adapter = new ArrayAdapter<String> (getActivity (), android.R.layout.simple_dropdown_item_1line, tvServiceProvider);
                list.setAdapter (adapter);
                list.setOnItemClickListener (new AdapterView.OnItemClickListener () {
                    @Override
                    public void onItemClick (AdapterView<?> parent, View view, int position,
                                             long id) {
                        switch (position) {
                            case 0:
                                flag = 3;
                                break;
                            case 1:
                                flag = 4;
                                break;
                            case 2:
                                flag = 5;
                                break;
                            case 3:
                                flag = 6;
                                break;
                        }
                        Intent intent = new Intent (getActivity (), SupportPageActivity.class);
                        intent.putExtra ("FLAG", flag);
                        startActivity (intent);
                        // Toast.makeText(getActivity(), String.valueOf(flag), Toast.LENGTH_LONG).show();
                    }
                });

                break;
            case "4":
                adapter = new ArrayAdapter<String> (getActivity (), android.R.layout.simple_dropdown_item_1line, tvDoorStep);
                list.setAdapter (adapter);
                list.setOnItemClickListener (new AdapterView.OnItemClickListener () {
                    @Override
                    public void onItemClick (AdapterView<?> parent, View view, int position,
                                             long id) {
                        switch (position) {
                            case 0:
                                flag = 7;
                                break;
                        }
                        Intent intent = new Intent (getActivity (), SupportPageActivity.class);
                        intent.putExtra ("FLAG", flag);
                        startActivity (intent);
                        //Toast.makeText(getActivity(), String.valueOf(flag), Toast.LENGTH_LONG).show();
                    }
                });

                break;
            case "5":
                adapter = new ArrayAdapter<String> (getActivity (), android.R.layout.simple_dropdown_item_1line, tvPickup);
                list.setAdapter (adapter);
                list.setOnItemClickListener (new AdapterView.OnItemClickListener () {
                    @Override
                    public void onItemClick (AdapterView<?> parent, View view, int position,
                                             long id) {
                        switch (position) {
                            case 0:
                                flag = 8;
                                break;
                        }
                        Intent intent = new Intent (getActivity (), SupportPageActivity.class);
                        intent.putExtra ("FLAG", flag);
                        startActivity (intent);
                        //  Toast.makeText(getActivity(), String.valueOf(flag), Toast.LENGTH_LONG).show();
                    }
                });

                break;
            case "6":
                adapter = new ArrayAdapter<String> (getActivity (), android.R.layout.simple_dropdown_item_1line, tvCarClean);
                list.setAdapter (adapter);
                list.setOnItemClickListener (new AdapterView.OnItemClickListener () {
                    @Override
                    public void onItemClick (AdapterView<?> parent, View view, int position,
                                             long id) {
                        switch (position) {
                            case 0:
                                flag = 9;
                                break;
                            case 1:
                                flag = 10;
                                break;
                            case 2:
                                flag = 11;
                                break;
                            case 3:
                                flag = 12;
                                break;
                            case 4:
                                flag = 13;
                                break;
                            case 5:
                                flag = 14;
                                break;
                        }
                        Intent intent = new Intent (getActivity (), SupportPageActivity.class);
                        intent.putExtra ("FLAG", flag);
                        startActivity (intent);
                        //  Toast.makeText(getActivity(), String.valueOf(flag), Toast.LENGTH_LONG).show();
                    }
                });

                break;
            case "7":
                adapter = new ArrayAdapter<String> (getActivity (), android.R.layout.simple_dropdown_item_1line, tvWheels);
                list.setAdapter (adapter);
                list.setOnItemClickListener (new AdapterView.OnItemClickListener () {
                    @Override
                    public void onItemClick (AdapterView<?> parent, View view, int position,
                                             long id) {
                        switch (position) {
                            case 0:
                                flag = 15;
                                break;
                            case 1:
                                flag = 16;
                                break;
                        }
                        Intent intent = new Intent (getActivity (), SupportPageActivity.class);
                        intent.putExtra ("FLAG", flag);
                        startActivity (intent);
                        //   Toast.makeText(getActivity(), String.valueOf(flag), Toast.LENGTH_LONG).show();
                    }
                });
                break;
            case "8":
                adapter = new ArrayAdapter<String> (getActivity (), android.R.layout.simple_dropdown_item_1line, tvRegularMaint);
                list.setAdapter (adapter);
                list.setOnItemClickListener (new AdapterView.OnItemClickListener () {
                    @Override
                    public void onItemClick (AdapterView<?> parent, View view, int position,
                                             long id) {
                        switch (position) {
                            case 0:
                                flag = 17;
                                break;
                            case 1:
                                flag = 18;
                                break;
                            case 2:
                                flag = 19;
                                break;
                            case 3:
                                flag = 20;
                                break;
                        }
                        Intent intent = new Intent (getActivity (), SupportPageActivity.class);
                        intent.putExtra ("FLAG", flag);
                        startActivity (intent);
                        //  Toast.makeText(getActivity(), String.valueOf(flag), Toast.LENGTH_LONG).show();
                    }
                });

                break;
            case "9":
                adapter = new ArrayAdapter<String> (getActivity (), android.R.layout.simple_dropdown_item_1line, tvBreakdown);
                list.setAdapter (adapter);
                list.setOnItemClickListener (new AdapterView.OnItemClickListener () {
                    @Override
                    public void onItemClick (AdapterView<?> parent, View view, int position,
                                             long id) {
                        switch (position) {
                            case 0:
                                flag = 21;
                                break;
                        }
                        Intent intent = new Intent (getActivity (), SupportPageActivity.class);
                        intent.putExtra ("FLAG", flag);
                        startActivity (intent);
                        //   Toast.makeText(getActivity(), String.valueOf(flag), Toast.LENGTH_LONG).show();
                    }
                });

                break;

            case "10":
                adapter = new ArrayAdapter<String> (getActivity (), android.R.layout.simple_dropdown_item_1line, tvServiceRequest);
                list.setAdapter (adapter);
                list.setOnItemClickListener (new AdapterView.OnItemClickListener () {
                    @Override
                    public void onItemClick (AdapterView<?> parent, View view, int position,
                                             long id) {
                        switch (position) {
                            case 0:
                                flag = 22;
                                break;
                            case 1:
                                flag = 23;
                                break;
                            case 2:
                                flag = 24;
                                break;
                            case 3:
                                flag = 25;
                                break;
                        }
                        Intent intent = new Intent (getActivity (), SupportPageActivity.class);
                        intent.putExtra ("FLAG", flag);
                        startActivity (intent);
                        //   Toast.makeText(getActivity(), String.valueOf(flag), Toast.LENGTH_LONG).show();
                    }
                });
                break;
        }
    }

    @Override
    public void onResume () {
        super.onResume ();
        getView ().setFocusableInTouchMode (true);
        getView ().requestFocus ();
        getView ().setOnKeyListener (new View.OnKeyListener () {
            @Override
            public boolean onKey (View v, int keyCode, KeyEvent event) {
                if (event.getAction () == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        // getActivity().finish();
                        FragmentManager fragmentManager = getFragmentManager ();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction ();
                        Support_1_Fragment f1 = new Support_1_Fragment ();
                        fragmentTransaction.replace (R.id.fragment_container, f1, "Fragment_Support");
                        fragmentTransaction.commit ();
                        return true;
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void onClick (View v) {

    }
}


