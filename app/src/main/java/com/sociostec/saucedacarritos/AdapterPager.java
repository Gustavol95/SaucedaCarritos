package com.sociostec.saucedacarritos;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Tavo on 30/09/2016.
 */
public class AdapterPager extends PagerAdapter {

    Context ctx;
    LayoutInflater inflater;
    ArrayList<Carro> carros;

    public AdapterPager(Context ctx, ArrayList<Carro> carros)
    {
        inflater=LayoutInflater.from(ctx);
        this.carros=carros;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View row = inflater.inflate(R.layout.page_car, container, false);
        TextView marca=(TextView) row.findViewById(R.id.marca);
        TextView modelo=(TextView) row.findViewById(R.id.modelo);
        TextView año=(TextView) row.findViewById(R.id.año);
        TextView color=(TextView) row.findViewById(R.id.color);

        marca.setText(carros.get(position).getMarca());
        modelo.setText(carros.get(position).getModelo());
        año.setText(carros.get(position).getAño());
        color.setText(carros.get(position).getColor());

        container.addView(row);

        return row;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return carros.size();
    }

}
