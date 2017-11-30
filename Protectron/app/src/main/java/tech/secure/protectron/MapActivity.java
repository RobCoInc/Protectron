package tech.secure.protectron;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    LatLng mLocation;

    Bundle extras;


    String name;
    String city;
    String address;

    String fullAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        extras = getIntent().getExtras();

        city = extras.getString("location_city");
        address = extras.getString("location_address");
        name = extras.getString("location_name");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        fullAddress = "" + name + ", " + address + ", " + city + "";

        mLocation = getLocationFromAddress(getApplicationContext(), fullAddress);

        if(!(mLocation == null))
        {
            mMap.addMarker(new MarkerOptions().position(mLocation).title("Your Shift"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(mLocation));
        }

//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public LatLng getLocationFromAddress(Context context, String strAddress) {
        Geocoder coder= new Geocoder(context, Locale.CANADA);
        List<Address> address;
        LatLng p1 = null;

        if(coder.isPresent())
        {
            try
            {
                address = coder.getFromLocationName(strAddress, 5);
                if(address==null)
                {
                    return null;
                }
                Address location = address.get(0);
                location.getLatitude();
                location.getLongitude();

                p1 = new LatLng(location.getLatitude(), location.getLongitude());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return p1;
        }
        return p1;
    }
}
