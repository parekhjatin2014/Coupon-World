package com.example.app;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.myapp.R;
import com.example.app.model.Category;
import com.example.app.model.Outlet;

import java.text.DecimalFormat;
import java.util.List;

public class NearbyOutletAdapter extends ArrayAdapter<Outlet> {

    private static final String TAG = NearbyOutletAdapter.class.getSimpleName();

    /**
     * Layout inflater instance
     */
    private LayoutInflater mLayoutInflater;

    private ImageLoader mImageLoader;

    /**
     * Context instance
     */
    private Context mContext;

    public NearbyOutletAdapter(Context context, List<Outlet> outletList) {
        super(context, 0, outletList);
        mContext = context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mImageLoader = ImageCacheManager.getInstance(context).getImageLoader();
    }

    public void updateList(List<Outlet> outletList) {
        clear();
        addAll(outletList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Outlet outlet = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder holder = null; // view lookup cache stored in

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.outlet_row, parent, false);
            holder.brandTextView = (TextView) convertView.findViewById(R.id.outlet_name);
            holder.couponOfferTextView = (TextView) convertView.findViewById(R.id.outlet_offer);
            holder.distanceTextView = (TextView) convertView.findViewById(R.id.outlet_distance);
            holder.neighbourhoodNameTextView = (TextView) convertView.findViewById(R.id.outlet_locality);
            holder.logoImage = (ImageView) convertView.findViewById(R.id.outlet_image);
            //holder.coverImage = (ImageView) convertView.findViewById(R.id.cover_img);
            holder.categoryLayout = (TextView) convertView.findViewById(R.id.outlet_category_type);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String brandText = outlet.getBrandName();
        if (TextUtils.isEmpty(brandText)) {
            holder.brandTextView.setVisibility(View.GONE);
        } else {
            holder.brandTextView.setVisibility(View.VISIBLE);
            holder.brandTextView.setText(brandText);
        }

        int couponsCount = outlet.getNumCoupons();
        if (couponsCount > 0) {
            holder.couponOfferTextView.setVisibility(View.VISIBLE);
            if (couponsCount == 0) {
                holder.couponOfferTextView.setText(String.valueOf(couponsCount) + " Offer");
            } else {
                holder.couponOfferTextView.setText(String.valueOf(couponsCount) + " Offers");
            }
        } else {
            holder.couponOfferTextView.setVisibility(View.GONE);
        }

        String neighbourhoodNameText = outlet.getNeighbourhoodName();
        if (TextUtils.isEmpty(brandText)) {
            holder.neighbourhoodNameTextView.setVisibility(View.GONE);
        } else {
            holder.neighbourhoodNameTextView.setVisibility(View.VISIBLE);
            holder.neighbourhoodNameTextView.setText(neighbourhoodNameText);
        }

        /*int locDistance = (int) outlet.getDistanceFromCurrent();
        holder.distanceTextView.setText(locDistance + " Km");*/

        double locDistance = (int) outlet.getDistanceFromCurrent();
        if (locDistance > 1000) {
            String distance = String.valueOf(new DecimalFormat("##.##").format(locDistance / 1000));
            holder.distanceTextView.setText(distance + " Km");
        } else {
            String distance = String.valueOf(locDistance);
            holder.distanceTextView.setText(distance + " Mtr");
        }


        String logoUrl = outlet.getLogoURL();
        if (!TextUtils.isEmpty(logoUrl)) {
            final ViewHolder finalViewHolder = holder;
            mImageLoader.get(logoUrl, new ImageLoader.ImageListener() {

                public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                    if (imageContainer.getBitmap() != null) {
                        if (finalViewHolder.logoImage.getVisibility() == View.GONE) {
                            finalViewHolder.logoImage.setVisibility(View.VISIBLE);
                        }
                        finalViewHolder.logoImage.setImageBitmap(imageContainer.getBitmap());
                    } else {
                        finalViewHolder.logoImage.setVisibility(View.VISIBLE);
                        finalViewHolder.logoImage.setImageBitmap(null);
                    }
                }


                public void onErrorResponse(VolleyError volleyError) {
                    //Set error image
                    finalViewHolder.logoImage.setVisibility(View.GONE);
                }
            });
        }
        String coverImgUrl = outlet.getCoverURL();

        /*if (!TextUtils.isEmpty(coverImgUrl)) {
            final ViewHolder viewHolder = holder;
            mImageLoader.get(coverImgUrl, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                    if (imageContainer.getBitmap() != null) {
                        if (viewHolder.coverImage.getVisibility() == View.GONE) {
                            viewHolder.coverImage.setVisibility(View.VISIBLE);
                        }
                        viewHolder.coverImage.setImageBitmap(imageContainer.getBitmap());
                    } else {
                        viewHolder.coverImage.setVisibility(View.VISIBLE);
                        viewHolder.coverImage.setImageBitmap(null);
                    }
                }

                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    //Set error image
                    viewHolder.coverImage.setVisibility(View.GONE);
                }
            });
        }*/
        //holder.categoryLayout.removeAllViews();
        List<Category> categoryArrayList = outlet.getCategories();
        String cat = "";
        for (Category category : outlet.getCategories()) {
            cat += category.getName() + " | ";
        }
        holder.categoryLayout.setText(cat);

        return convertView;

    }


    /**
     * view holder class to hold the item view
     */
    private static class ViewHolder {

        private TextView brandTextView;
        private TextView couponOfferTextView;
        private TextView distanceTextView;
        private TextView neighbourhoodNameTextView;
        private ImageView logoImage;
        //private ImageView coverImage;
        private TextView categoryLayout;
    }
}
