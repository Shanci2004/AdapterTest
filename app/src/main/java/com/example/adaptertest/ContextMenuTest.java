package com.example.adaptertest;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContextMenuTest extends AppCompatActivity {

    private ListView listView;
    private SimpleAdapter simpleAdapter;
    private ActionMode actionMode;
    // 用于记录选中的列表项位置
    private ArrayList<Integer> selectedItems = new ArrayList<>();
    // 用于在ActionBar上显示选中数量的TextView
    private TextView selectedCountTextView;

    private String[] names = new String[]{"Lion", "Tiger", "Monkey", "Dog", "Cat", "Elephant"};
    private int[] images = new int[]{R.drawable.lion, R.drawable.tiger, R.drawable.monkey, R.drawable.dog, R.drawable.cat, R.drawable.elephant};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_menu_test);

        // 创建列表数据
        List<Map<String, Object>> listItems = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> listItem = new HashMap<>();
            listItem.put("name", names[i]);
            listItem.put("image", images[i]);
            listItems.add(listItem);
        }

        // 设置列表适配器
        simpleAdapter = new SimpleAdapter(ContextMenuTest.this, listItems, R.layout.context_menu_item,
                new String[]{"name", "image"}, new int[]{R.id.menu_name, R.id.menu_image});

        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(simpleAdapter);

        // 设置ListView的多选模式监听器
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                if (checked) {
                    selectedItems.add(position);
                } else {
                    selectedItems.remove(Integer.valueOf(position));
                }
                // 更新ActionBar上显示的选中数量
                updateSelectedCountTextView();
                mode.setTitle(selectedItems.size() + " 个已选中");
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater menuInflater = getMenuInflater();
                menuInflater.inflate(R.menu.menu_context, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                if (item.getItemId() == R.id.menu_delete) {
                    // 执行删除选中项操作
                    deleteSelectedItems();
                    mode.finish();
                    return true;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                actionMode = null;
                // 清除选中状态
                selectedItems.clear();
                // 更新列表显示
                simpleAdapter.notifyDataSetChanged();
                // 更新ActionBar上显示的选中数量
                updateSelectedCountTextView();
            }
        });

        // 获取ActionBar并设置自定义视图
        ActionBar actionBar = getActionBar();
        if (actionBar!= null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setCustomView(R.layout.action_bar_layout);
            selectedCountTextView = actionBar.getCustomView().findViewById(R.id.selected_count_textview);
        }
    }

    // 更新ActionBar上显示选中数量的TextView
    private void updateSelectedCountTextView() {
        if (selectedCountTextView!= null) {
            selectedCountTextView.setText("已选中: " + selectedItems.size() + " 个");
        }
    }

    // 删除选中的列表项
    private void deleteSelectedItems() {
        List<Map<String, Object>> newListItems = new ArrayList<>();
        for (int i = 0; i < simpleAdapter.getCount(); i++) {
            if (!selectedItems.contains(i)) {
                Map<String, Object> listItem = new HashMap<>();
                listItem.put("name", ((Map<String, Object>) simpleAdapter.getItem(i)).get("name"));
                listItem.put("image", ((Map<String, Object>) simpleAdapter.getItem(i)).get("image"));
                newListItems.add(listItem);
            }
        }
        simpleAdapter = new SimpleAdapter(this, newListItems, R.layout.context_menu_item,
                new String[]{"name", "image"}, new int[]{R.id.menu_name, R.id.menu_image});
        listView.setAdapter(simpleAdapter);
        // 清除选中状态
        selectedItems.clear();
        // 更新ActionBar上显示的选中数量
        updateSelectedCountTextView();
    }

}