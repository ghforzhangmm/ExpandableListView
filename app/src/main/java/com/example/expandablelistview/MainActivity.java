package com.example.expandablelistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
/**
 * @email shexiaoheng@163.com
 * @blog <a href='http://blog.csdn.net/shexiaoheng'>http://blog.csdn.net/shexiaoheng</a >
 * @Detail 本Demo为ExpandableListView嵌套ExpandableListView实现三级菜单的例子
 * #ParentAdapter.OnChildTreeViewClickListener
 */
public class MainActivity extends AppCompatActivity implements ExpandableListView.OnGroupExpandListener, ParentAdapter.OnChildTreeViewClickListener {
    /**‘
     * 临时数组,提供给列表显示*/
    private static String str[] = {"电容屏","UPS","荣福","电容屏","UPS","荣福"};
    private Context mContext;
    private ExpandableListView eList;
    private ArrayList<ParentEntity> parents;
    private ParentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        loadData();//获取数据，假数据
        initEList();//初始化

    }

    /**
     * 初始化菜单数据源
     */
    private void loadData() {

        parents = new ArrayList<ParentEntity>();

        ParentEntity parent = new ParentEntity(); //父类对象
        parent.setGroupName("巡检点");//假数据 ，设置一级列表数据
        parent.setGroupColor(getResources().getColor(android.R.color.holo_red_light));//红色 ，一级列表颜色

        ArrayList<ChildEntity> childs = new ArrayList<ChildEntity>();
        for (int j = 0; j < 6; j++) {
            ChildEntity child = new ChildEntity(); //子类对象
            child.setGroupName(str[j]);//假数据 ，二级列表数据
            child.setGroupColor(Color.parseColor("#ff00ff"));//紫色   二级列表颜色

            ArrayList<GrandsonEntity> childNames = new ArrayList<GrandsonEntity>();
            ArrayList<Integer> childColors = new ArrayList<Integer>();

            for (int k = 0; k < 5; k++) {
                GrandsonEntity grandsonEntity = new GrandsonEntity();
                grandsonEntity.setName1("外观以及清洁检查.改动");
                grandsonEntity.setName2("UPS周围有无易燃易爆物品");
                childNames.add(grandsonEntity);//假数据 三级列表数据
                childColors.add(Color.parseColor("#ff00ff"));//紫色

            }
            child.setChildNames(childNames);
            childs.add(child);
        }
        parent.setChilds(childs);
        parents.add(parent);
    }

    /**
     * 初始化ExpandableListView
     */
    private void initEList() {
        eList = (ExpandableListView) findViewById(R.id.eList);
        eList.setOnGroupExpandListener(this);
        adapter = new ParentAdapter(mContext, parents);//父类分组适配器
        eList.setAdapter(adapter);
        adapter.setOnChildTreeViewClickListener(this);//设置条目点击事件

    }

    /**
     * 点击子ExpandableListView的子项时，回调本方法，根据下标获取值来做相应的操作
     */
    @Override
    public void onClickPosition(int parentPosition, int groupPosition, int childPosition) {
        // do something
        String childName = parents.get(parentPosition).getChilds()
                .get(groupPosition).getChildNames().get(childPosition)
                .toString();
        Toast.makeText(
                mContext,
                "点击的下标为： parentPosition=" + parentPosition
                        + "   groupPosition=" + groupPosition
                        + "   childPosition=" + childPosition + "\n点击的是："
                        + childName, Toast.LENGTH_SHORT).show();
    }

    /**
     * 展开一项，关闭其他项，保证每次只能展开一项
     */
    @Override
    public void onGroupExpand(int groupPosition) {
        for (int i = 0; i < parents.size(); i++) {
            if (i != groupPosition) {
//                eList.collapseGroup(i);//关闭状态
            }
        }
    }
}