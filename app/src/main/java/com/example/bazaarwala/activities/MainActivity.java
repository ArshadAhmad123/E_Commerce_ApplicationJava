package com.example.bazaarwala.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bazaarwala.R;
import com.example.bazaarwala.adapters.CatagoryAdapter;
import com.example.bazaarwala.adapters.DesignAdapter;
import com.example.bazaarwala.adapters.FeatureAdapter;
import com.example.bazaarwala.adapters.MegaDiscountAdapter;
import com.example.bazaarwala.adapters.ProductAdapter;
import com.example.bazaarwala.adapters.RoundShapeAdapter;
import com.example.bazaarwala.adapters.SliderAdapter;
import com.example.bazaarwala.adapters.SliderAdapter2;
import com.example.bazaarwala.adapters.SponscorAdapter;
import com.example.bazaarwala.adapters.ViewFeatureAdapter;
import com.example.bazaarwala.databinding.ActivityMainBinding;
import com.example.bazaarwala.model.Catagory;
import com.example.bazaarwala.model.MegaDiscount;
import com.example.bazaarwala.model.Product;
import com.example.bazaarwala.model.Slider2;
import com.example.bazaarwala.model.Url;
import com.example.bazaarwala.model.slider;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ViewFeatureAdapter viewFeatureAdapter;
    CatagoryAdapter catagoryAdapter;
    SponscorAdapter sponscorAdapter;
    RoundShapeAdapter roundShapeAdapter;
    ArrayList<Catagory> catagories;
    ArrayList<Catagory> catagories1;
    MegaDiscountAdapter megaDiscountAdapter;
    ArrayList<MegaDiscount> megaDiscounts;
    Handler sliderHandler = new Handler();

    DesignAdapter designAdapter;

    ArrayList<MegaDiscount> arrayListDesign;
    ProductAdapter productAdapter;
    ArrayList<Product> products;
    ViewPager2 viewPager2;
    ViewPager2 viewPager3,viewPager;
    ArrayList<slider> sliders;
    SliderAdapter sliderAdapter;
    FeatureAdapter featureAdapter;
    ArrayList<Slider2> sliders2;
    SliderAdapter2 sliderAdapter2;
    Handler handler= new Handler();
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initCatagories();
        initCatagories1();
        initProducts();
        initSliders();
        initSliders2();
        catagoryList();
        megaDiscount();
        megaDiscount1();
        megaDiscount2();
        featuredBrand();
        sponcerBrand();
        sponcerBrand1();
        sponcerBrand2();
        sponcerBrand3();
        featuredBrand1();
        newDesign();
        searchView=findViewById(R.id.searchBar);
        searchView.setFocusable(false);
        searchView.setIconified(true);
        searchView.requestFocusFromTouch();
        binding.bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CartActivity.class));
            }
        });
        binding.bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id= item.getItemId();
                if (id==R.id.home){
                    Intent intent= new Intent(MainActivity.this,MainActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent= new Intent(MainActivity.this, ProfileUser.class);
                    startActivity(intent);
                }
                return false;
            }
        });

      binding.login.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent= new Intent(MainActivity.this,SignUpActivity.class);
              startActivity(intent);
          }
      });
        binding.fashion.setTextColor(getResources().getColor(R.color.white));
        GradientDrawable gradientDrawable = (GradientDrawable) binding.fashion.getBackground().mutate();
        gradientDrawable.setColor(Color.BLACK);
      binding.fashion.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              binding.fashion.setTextColor(getResources().getColor(R.color.white));
              GradientDrawable gradientDrawable = (GradientDrawable) binding.fashion.getBackground().mutate();
              gradientDrawable.setColor(Color.BLACK);
              binding.beauty.setTextColor(getResources().getColor(R.color.black));
              GradientDrawable gradientDrawable1 = (GradientDrawable) binding.beauty.getBackground().mutate();
              gradientDrawable1.setColor(Color.WHITE);
          }
      });
      binding.beauty.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              binding.fashion.setTextColor(getResources().getColor(R.color.black));
              GradientDrawable gradientDrawable = (GradientDrawable) binding.fashion.getBackground().mutate();
              gradientDrawable.setColor(Color.WHITE);
              binding.beauty.setTextColor(getResources().getColor(R.color.white));
              GradientDrawable gradientDrawable1 = (GradientDrawable) binding.beauty.getBackground().mutate();
              gradientDrawable1.setColor(Color.BLACK);
              startActivity(new Intent(MainActivity.this,Beauty.class));
              binding.fashion.setTextColor(getResources().getColor(R.color.white));
              gradientDrawable.setColor(Color.BLACK);
              binding.beauty.setTextColor(getResources().getColor(R.color.black));
              gradientDrawable1.setColor(Color.WHITE);

          }
      });
        binding.searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent= new Intent(MainActivity.this,SearchActivity.class);
                intent.putExtra("query",query.toString());
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });

    }

    private void newDesign() {
        arrayListDesign  = new ArrayList<>();
        arrayListDesign.add(new MegaDiscount(R.drawable.design1));
        arrayListDesign.add(new MegaDiscount(R.drawable.design3));
        arrayListDesign.add(new MegaDiscount(R.drawable.design5));
        arrayListDesign.add(new MegaDiscount(R.drawable.design7));
        arrayListDesign.add(new MegaDiscount(R.drawable.design9));
        arrayListDesign.add(new MegaDiscount(R.drawable.design2));
        arrayListDesign.add(new MegaDiscount(R.drawable.design4));
        arrayListDesign.add(new MegaDiscount(R.drawable.design6));
        arrayListDesign.add(new MegaDiscount(R.drawable.design8));
        arrayListDesign.add(new MegaDiscount(R.drawable.design10));
        designAdapter = new DesignAdapter(MainActivity.this,arrayListDesign);
        GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this,2, RecyclerView.HORIZONTAL,false);
        binding.designRecyle.setLayoutManager(layoutManager);
        binding.designRecyle.setAdapter(designAdapter);

    }

    private void catagoryList() {
        megaDiscounts = new ArrayList<>();
        megaDiscounts.add(new MegaDiscount("Fire Bolt","BOAT","","Watches",R.drawable.smart_watch));
        megaDiscounts.add(new MegaDiscount("Anouk","WROGN","","women's Sarees",R.drawable.women_saree));
        megaDiscounts.add(new MegaDiscount("Kalini","MAX","","women's Kurtas",R.drawable.women_kurta));
        megaDiscounts.add(new MegaDiscount("Puma","ADIDAS","","Sports Shoes",R.drawable.sport_shoes));
        megaDiscounts.add(new MegaDiscount("Safare","TEAKWOOD","","Trolly Bags",R.drawable.trolly_bag));
        megaDiscounts.add(new MegaDiscount("Baggit","LAVIE","","Women's Walllets",R.drawable.women_wallets));
        megaDiscounts.add(new MegaDiscount("PONDS","LAKEMI","","Women's Beauty",R.drawable.beauty1));
        megaDiscounts.add(new MegaDiscount("Fire Bolt","BOAT","","Watches",R.drawable.smart_watch));

    }


    private void sponcerBrand3() {
         featureAdapter = new FeatureAdapter(MainActivity.this,megaDiscounts);
        LinearLayoutManager layoutManager= new LinearLayoutManager(MainActivity.this,RecyclerView.HORIZONTAL,false);
        binding.sponcerBrand3.setLayoutManager(layoutManager);
        binding.sponcerBrand3.setAdapter(featureAdapter);
    }

    private void sponcerBrand2() {
        featureAdapter = new FeatureAdapter(MainActivity.this,megaDiscounts);
        LinearLayoutManager layoutManager= new LinearLayoutManager(MainActivity.this,RecyclerView.HORIZONTAL,false);
        binding.sponcerBrand2.setLayoutManager(layoutManager);
        binding.sponcerBrand2.setAdapter(featureAdapter);
    }

    private void sponcerBrand1() {
        sponscorAdapter = new SponscorAdapter(MainActivity.this,megaDiscounts);
        LinearLayoutManager layoutManager= new LinearLayoutManager(MainActivity.this,RecyclerView.HORIZONTAL,false);
        binding.sponcerBrand1.setLayoutManager(layoutManager);
        binding.sponcerBrand1.setAdapter(sponscorAdapter);
    }
    private void sponcerBrand() {
        sponscorAdapter = new SponscorAdapter(MainActivity.this,megaDiscounts);
        LinearLayoutManager layoutManager= new LinearLayoutManager(MainActivity.this,RecyclerView.HORIZONTAL,false);
        binding.sponcerBrand.setLayoutManager(layoutManager);
        binding.sponcerBrand.setAdapter(sponscorAdapter);
    }
    private void featuredBrand1() {
        viewPager = findViewById(R.id.featuredBrand1);
        viewFeatureAdapter = new ViewFeatureAdapter(viewPager,megaDiscounts);
        viewPager.setAdapter(viewFeatureAdapter);
        viewPager.setClipChildren(false);
        viewPager.setClipToPadding(false);
        viewPager.setOffscreenPageLimit(3);
        viewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r= 1-Math.abs(position);
                page.setScaleY(0.85f+r*0.15f);
            }
        });
        viewPager.setPageTransformer(compositePageTransformer);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable1);
                handler.postDelayed(runnable1,2000);
            }
        });
    }
    private Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
        }
    };
    private void featuredBrand() {
        megaDiscountAdapter = new MegaDiscountAdapter(MainActivity.this,megaDiscounts);
        LinearLayoutManager layoutManager= new LinearLayoutManager(MainActivity.this,RecyclerView.HORIZONTAL,false);
        binding.featuredBrand.setLayoutManager(layoutManager);
        binding.featuredBrand.setAdapter(megaDiscountAdapter);
    }
    private void megaDiscount() {
        megaDiscountAdapter = new MegaDiscountAdapter(MainActivity.this,megaDiscounts);
        GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this,2, RecyclerView.HORIZONTAL,false);
        binding.catagoriesList3.setLayoutManager(layoutManager);
        binding.catagoriesList3.setAdapter(megaDiscountAdapter);
    }
    private void megaDiscount2() {
        roundShapeAdapter = new RoundShapeAdapter(MainActivity.this,megaDiscounts);
        GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this,2, RecyclerView.HORIZONTAL,false);
        binding.catagoriesList4.setLayoutManager(layoutManager);
        binding.catagoriesList4.setAdapter(roundShapeAdapter);
    }
    private void megaDiscount1() {
        megaDiscountAdapter = new MegaDiscountAdapter(MainActivity.this,megaDiscounts);
        LinearLayoutManager layoutManager= new LinearLayoutManager(MainActivity.this,RecyclerView.HORIZONTAL,false);
        binding.megaDiscount2.setLayoutManager(layoutManager);
        binding.megaDiscount2.setAdapter(megaDiscountAdapter);
    }

    private void initSliders2() {
        viewPager3 = findViewById(R.id.ViewPager3);
        sliders2 = new ArrayList<>();
        sliders2.add(new Slider2("",R.drawable.sl1));
        sliders2.add(new Slider2("",R.drawable.sl2));
        sliders2.add(new Slider2("",R.drawable.sl3));
        sliders2.add(new Slider2("",R.drawable.sl4));
        sliders2.add(new Slider2("",R.drawable.sl5));
        sliderAdapter2 = new SliderAdapter2(this,viewPager3,sliders2);
        viewPager3.setAdapter(sliderAdapter2);
        viewPager3.setClipChildren(false);
        viewPager3.setClipToPadding(false);
        viewPager3.setOffscreenPageLimit(3);
        viewPager3.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        viewPager3.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(runnable);
                sliderHandler.postDelayed(runnable,2000);
            }
        });
    }
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
        viewPager3.setCurrentItem(viewPager3.getCurrentItem()+1);
        }
    };

    private void initSliders() {
        viewPager2 = findViewById(R.id.viewPager2);
        sliders = new ArrayList<>();
        sliders.add(new slider("",R.drawable.off2));
        sliders.add(new slider("",R.drawable.off5));
        sliders.add(new slider("",R.drawable.off3));
        sliders.add(new slider("",R.drawable.off4));
        sliders.add(new slider("",R.drawable.off2));
        sliders.add(new slider("",R.drawable.off5));
        sliders.add(new slider("",R.drawable.off3));
        sliders.add(new slider("",R.drawable.off4));
        sliders.add(new slider("",R.drawable.off2));
        sliders.add(new slider("",R.drawable.off5));
        sliders.add(new slider("",R.drawable.off3));
        sliders.add(new slider("",R.drawable.off4));
        sliders.add(new slider("",R.drawable.off2));
        sliders.add(new slider("",R.drawable.off5));
        sliders.add(new slider("",R.drawable.off3));
        sliders.add(new slider("",R.drawable.off4));
        sliders.add(new slider("",R.drawable.off2));
        sliders.add(new slider("",R.drawable.off5));
        sliders.add(new slider("",R.drawable.off3));
        sliders.add(new slider("",R.drawable.off4));
        sliders.add(new slider("",R.drawable.off2));
        sliders.add(new slider("",R.drawable.off5));
        sliders.add(new slider("",R.drawable.off3));
        sliders.add(new slider("",R.drawable.off4));
        sliders.add(new slider("",R.drawable.off2));
        sliders.add(new slider("",R.drawable.off5));
        sliders.add(new slider("",R.drawable.off3));
        sliders.add(new slider("",R.drawable.off4));
        sliders.add(new slider("",R.drawable.off2));
        sliders.add(new slider("",R.drawable.off5));
        sliders.add(new slider("",R.drawable.off3));
        sliders.add(new slider("",R.drawable.off4));
        sliderAdapter = new SliderAdapter(viewPager2,sliders);
        viewPager2.setAdapter(sliderAdapter);
        viewPager2.setOffscreenPageLimit(2);
        viewPager2.setClipChildren(false);
        viewPager2.setClipToPadding(false);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        autoSlider();
    }

    private void autoSlider() {
        final  long delay = 2000;
        final  long period = 2000;
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(viewPager2.getCurrentItem() ==sliders.size()-1){
                    viewPager2.setCurrentItem(0);
                }else {
                    viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1,true);
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);}},delay,period);

    }

    void initCatagories1(){
        catagories1 = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.Catagory_Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.optJSONArray("response_obj");
                    Catagory catagory;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                        catagory = new Catagory(
                                jsonObject1.getString("name"),
                                Url.Main_Url+"images/"+jsonObject1.getString("icon"),
                                jsonObject1.getString("color"),
                                jsonObject1.getString("brief"),
                                jsonObject1.getInt("id")
                        );


                        catagories1.add(catagory);
                    }
                    Collections.reverse(catagories1);

                    catagoryAdapter = new CatagoryAdapter(MainActivity.this,catagories1);
                    LinearLayoutManager layoutManager= new LinearLayoutManager(MainActivity.this,RecyclerView.HORIZONTAL,false);
                    binding.catagoriesList1.setLayoutManager(layoutManager);
                    binding.catagoriesList1.setAdapter(catagoryAdapter);

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);


    }
    void initCatagories(){
        catagories = new ArrayList<>();

      StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.Catagory_Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.optJSONArray("response_obj");
                    Catagory catagory;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                      catagory = new Catagory(
                              jsonObject1.getString("name"),
                              Url.Main_Url+"images/"+jsonObject1.getString("icon"),
                              jsonObject1.getString("color"),
                              jsonObject1.getString("brief"),
                              jsonObject1.getInt("id")
                      );


                    catagories.add(catagory);
                    }
                    catagoryAdapter = new CatagoryAdapter(MainActivity.this,catagories);
                    LinearLayoutManager layoutManager= new LinearLayoutManager(MainActivity.this,RecyclerView.HORIZONTAL,false);
                    binding.catagoriesList.setLayoutManager(layoutManager);

                    binding.catagoriesList.setAdapter(catagoryAdapter);

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);


    }



    void initProducts(){
        products = new ArrayList<>();
          StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.Product_Url, new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {
                  try {

                      JSONObject jsonObject = new JSONObject(response);
                      JSONArray jsonArray = jsonObject.optJSONArray("response_obj");
                      Product product;
                      for (int i = 0; i < jsonArray.length(); i++) {
                          JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                          product = new Product(
                                  jsonObject1.getString("name"),
                                  Url.Main_Url+"images/" + jsonObject1.getString("image"),
                                  jsonObject1.getString("status"),
                                  jsonObject1.getDouble("price"),
                                  jsonObject1.getDouble("discount"),
                                  jsonObject1.getInt("stock"),
                                  jsonObject1.getInt("id")
                          );
                          products.add(product);

                      }
                      productAdapter = new ProductAdapter(getApplicationContext(), products);
                      GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 2);
                      binding.productList.setLayoutManager(layoutManager);
                      binding.productList.setAdapter(productAdapter);


                  } catch (Exception e) {
                      Toast.makeText(MainActivity.this,e.toString(),Toast.LENGTH_SHORT).show();

                      e.printStackTrace();
                  }


              }

          }, new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {
        Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
              }
          });

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);


    }
}