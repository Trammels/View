package com.gefei.liu.view;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 日历显示activity
 */
public class CalendarActivity extends Activity implements OnGestureListener {

    private GestureDetector gestureDetector = null;
    private CalendarAdapter calV = null;
    private GridView gridView = null;
    private TextView topText = null, btn_goback_to_today;
    private static int jumpMonth = 0;      //每次滑动，增加或减去一个月,默认为0（即显示当前月）
    private static int jumpYear = 0;       //滑动跨越一年，则增加或者减去一年,默认为0(即当前年)
    private int year_c = 0;
    private int month_c = 0;
    private int day_c = 0;
    private String currentDate = "";
    private Bundle bd = null;//发送参数
    private Bundle bun = null;//接收参数
    private String ruzhuTime;
    private String lidianTime;
    private String state = "";
    private LinearLayout ll_back, btn_next_month, btn_prev_month;
    //	private WaitingDataFromRemote dataFromRemote;
    private SharedPreferences sp;
    private List<MyScheduleBean> schedules = new ArrayList<MyScheduleBean>();
    private String current;
    //
    String strYear;
    String strMonth;
    int intMonth;
    int intYear;
    int i;


    public CalendarActivity() {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
        currentDate = sdf.format(date);  //当期日期
        year_c = Integer.parseInt(currentDate.split("-")[0]);
        month_c = Integer.parseInt(currentDate.split("-")[1]);
        day_c = Integer.parseInt(currentDate.split("-")[2]);


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);
        BsApplication.context = CalendarActivity.this;
        BsApplication.display = getWindowManager().getDefaultDisplay();
        bd = new Bundle();//out
        bun = getIntent().getExtras();//in

        current = MyDateUtils.getDay(System.currentTimeMillis());
        if (bun != null && bun.getString("state").equals("ruzhu")) {
            state = bun.getString("state");
            System.out.println("%%%%%%" + state);
        } else if (bun != null && bun.getString("state").equals("lidian")) {

            state = bun.getString("state");
            System.out.println("|||||||||||" + state);
        }

        gestureDetector = new GestureDetector(this);
//		bd=new Bundle();
        addGridView();
        calV = new CalendarAdapter(this, getResources(), jumpMonth, jumpYear, year_c, month_c, day_c, schedules);
        gridView.setAdapter(calV);
        topText = (TextView) findViewById(R.id.tv_month);
        topText.setText(DateUtil.dateToStr(new Date(), "yyyy-MM"));
//		addTextToTopTextView(topText);

        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        ll_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });

        btn_goback_to_today = (TextView) findViewById(R.id.btn_goback_to_today);
        btn_goback_to_today.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //跳转到今天
                int xMonth = jumpMonth;
                int xYear = jumpYear;
                int gvFlag = 0;
                jumpMonth = 0;
                jumpYear = 0;
                addGridView();   //添加一个gridView
                year_c = Integer.parseInt(currentDate.split("-")[0]);
                month_c = Integer.parseInt(currentDate.split("-")[1]);
                day_c = Integer.parseInt(currentDate.split("-")[2]);
//	        	getData(current);
                calV = new CalendarAdapter(CalendarActivity.this, getResources(), jumpMonth, jumpYear, year_c, month_c, day_c, null);
                gridView.setAdapter(calV);
//		        addTextToTopTextView(topText);
                topText.setText(MyDateUtils.getDay(System.currentTimeMillis()));
                gvFlag++;
            }
        });

        btn_prev_month = (LinearLayout) findViewById(R.id.btn_prev_month);
        btn_next_month = (LinearLayout) findViewById(R.id.btn_next_month);

        btn_prev_month.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //点击左边的箭头
                int gvFlag = 0;
                addGridView();   //添加一个gridView
                jumpMonth--;     //上一个月
//				getData(getDay(false));
                calV = new CalendarAdapter(CalendarActivity.this, getResources(), jumpMonth, jumpYear, year_c, month_c, day_c, null);
                gridView.setAdapter(calV);
                gvFlag++;
//		        addTextToTopTextView(topText);
                getDay(false);
                topText.setText(current);

            }
        });

        btn_next_month.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //点击右边的箭头
                int gvFlag = 0;
                addGridView();   //添加一个gridView
                jumpMonth++;     //下一个月
//				getData(getDay(true));
                calV = new CalendarAdapter(CalendarActivity.this, getResources(), jumpMonth, jumpYear, year_c, month_c, day_c, null);
                gridView.setAdapter(calV);
//		        addTextToTopTextView(topText);

                getDay(true);

				topText.setText(current);

                gvFlag++;
            }
        });

        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//		getData(current);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        int gvFlag = 0;         //每次添加gridview到viewflipper中时给的标记
        if (e1.getX() - e2.getX() > 120) {
            //像左滑动
            addGridView();   //添加一个gridView
            jumpMonth++;     //下一个月
//		getData	(getDay(true));
            calV = new CalendarAdapter(this, getResources(), jumpMonth, jumpYear, year_c, month_c, day_c, null);
            gridView.setAdapter(calV);
//	        addTextToTopTextView(topText);
            topText.setText(current);
            gvFlag++;

            return true;
        } else if (e1.getX() - e2.getX() < -120) {
            //向右滑动
            addGridView();   //添加一个gridView
            jumpMonth--;     //上一个月
//			getData(getDay(false));
            calV = new CalendarAdapter(this, getResources(), jumpMonth, jumpYear, year_c, month_c, day_c, null);
            gridView.setAdapter(calV);
            gvFlag++;
//	        addTextToTopTextView(topText);
            topText.setText(current);

            return true;
        }
        return false;
    }
//设置左右箭头的头部时间，true为下个月，右箭头，false为上个月，左箭头
    private String getDay(boolean next) {
        String m = current.substring(5, current.length());
        String y = current.substring(0, 4);
        int year = Integer.parseInt(y);
        int month = Integer.parseInt(m);
        if (next) {
            month++;
        } else {
            month--;
        }
        if (month == 13) {
            current = (year + 1) + "-" + "01";
            return current;
        } else if (month == 0) {
            current = (year - 1) + "-" + "12";
            return current;
        } else {
            if (month < 10) {
                current = y + "-0" + month;
            } else {
                current = y + "-" + month;
            }
            return current;
        }
    }

    /**
     * 创建菜单
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0, menu.FIRST, menu.FIRST, "今天");
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 选择菜单
     */
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
            case Menu.FIRST:
                //跳转到今天
                int xMonth = jumpMonth;
                int xYear = jumpYear;
                int gvFlag = 0;
                jumpMonth = 0;
                jumpYear = 0;
                addGridView();   //添加一个gridView
                year_c = Integer.parseInt(currentDate.split("-")[0]);
                month_c = Integer.parseInt(currentDate.split("-")[1]);
                day_c = Integer.parseInt(currentDate.split("-")[2]);
//        	calV = new CalendarAdapter(this,getResources(),jumpMonth,jumpYear,year_c,month_c,day_c);
//	        gridView.setAdapter(calV);
                addTextToTopTextView(topText);
                gvFlag++;


                break;
        }
        return super.onMenuItemSelected(featureId, item);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return this.gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    //添加头部的年份 闰哪月等信息
    public void addTextToTopTextView(TextView view) {
        StringBuffer textDate = new StringBuffer();
        textDate.append(calV.getShowYear()).append("年").append(calV.getShowMonth()).append("月").append("\t");
        view.setText(textDate);
        view.setTextColor(Color.BLACK);
        view.setTypeface(Typeface.DEFAULT_BOLD);
    }

    //添加gridview
    private void addGridView() {

        gridView = (GridView) findViewById(R.id.gridview);

//		 //定义DisplayMetrics 对象
//        DisplayMetrics  dm = new DisplayMetrics();
//       //取得窗口属性
//        getWindowManager().getDefaultDisplay().getMetrics(dm);
//       //窗口的宽度
//        int screenWidth = dm.widthPixels;
//       //窗口高度
//        int screenHeight = dm.heightPixels;
//        int mHeight = screenHeight - gridView.getHeight();
//        ViewGroup.LayoutParams params = gridView.getLayoutParams();
//        params.height =mHeight;
//        gridView.setLayoutParams(params);

        gridView.setOnTouchListener(new OnTouchListener() {
            //将gridview中的触摸事件回传给gestureDetector
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                return CalendarActivity.this.gestureDetector.onTouchEvent(event);
            }
        });

        gridView.setOnItemClickListener(new OnItemClickListener() {
            //gridView中的每一个item的点击事件

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                //点击任何一个item，得到这个item的日期(排除点击的是周日到周六(点击不响应))
                int startPosition = calV.getStartPositon();
                int endPosition = calV.getEndPosition();
                if (startPosition <= position + 7 && position <= endPosition - 7) {
                    String scheduleDay = calV.getDateByClickItem(position).split("\\.")[0];  //这一天的阳历
                    String scheduleLunarDay = calV.getDateByClickItem(position).split("\\.")[1];  //这一天的阴历
                    String scheduleYear = calV.getShowYear();
                    String scheduleMonth = calV.getShowMonth();
                    Toast.makeText(CalendarActivity.this, scheduleYear + "-0" + scheduleMonth + "-" + scheduleDay, Toast.LENGTH_SHORT).show();
                    ruzhuTime = scheduleMonth + "月" + scheduleDay + "日";
                    lidianTime = scheduleMonth + "月" + scheduleDay + "日";
                    if(scheduleMonth.equals("10")||scheduleMonth.equals("11")||scheduleMonth.equals("12")){
                        topText.setText(scheduleYear + "-" + scheduleMonth + "");
                    }else{
                        topText.setText(scheduleYear + "-0" + scheduleMonth + "");
                    }

//	                Intent intent=new Intent();
                    if (state.equals("ruzhu")) {

                        bd.putString("ruzhu", ruzhuTime);
                        System.out.println("ruzhuuuuuu" + bd.getString("ruzhu"));
                    } else if (state.equals("lidian")) {

                        bd.putString("lidian", lidianTime);
                    }
//	                intent.setClass(CalendarActivity.this, HotelActivity.class);	             
//	                intent.putExtras(bd);
//	                startActivity(intent);
//	                finish();
                }
            }

        });
    }

    /**
     * 获取调度排班的数据
     **/
    private void getData(String currentDate) {
//		dataFromRemote = new WaitingDataFromRemote(CalendarActivity.this, new OnResponseResultListener() {
//			@Override
//			public void onResponseResult(String result) {
////				Log.d("===", "result="+result);
//				JSONObject json = JSONObject.parseObject(result);
//				int success = json.getIntValue("success");
//				if(success ==0){
//				MyScheduleListBean myScheduleList = JSON.parseObject(result, MyScheduleListBean.class);
//				if(myScheduleList.getSchedules().size()>0){
//					schedules.clear();
//					schedules.addAll(myScheduleList.getSchedules());
//
//
//				}
//				calV = new CalendarAdapter(CalendarActivity.this,getResources(),
//						jumpMonth,jumpYear,year_c,month_c,day_c,schedules);
//		        gridView.setAdapter(calV);
//
//				}
//			}
//		});
//
//		JSONObject json = new JSONObject();
//		json.put("driverId", sp.getString("userid", ""));
//		json.put("scheduleDate", currentDate);
////		Log.d("===", "currentData="+currentDate);
//		dataFromRemote.execute(Constants.DRIVER_SCHEDULING, json.toJSONString());
    }


}