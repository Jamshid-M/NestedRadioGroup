# NestedRadioGroup

[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-NestedRadioGroup-green.svg?style=flat )]( https://android-arsenal.com/details/1/7925 )

## RadioGroup which don't lose state with inner ViewGroup

#### You can add ViewGroup into RadioGroup to get complex views with RadioButtons. </br>

<img src="https://github.com/Jamshid-M/NestedRadioGroup/blob/master/assets/select.png" height="408" width="243"> <img src="https://github.com/Jamshid-M/NestedRadioGroup/blob/master/assets/unselect.png" height="408" width="243">

## Usage

Add it in your root build.gradle at the end of repositories
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
```
dependencies {
	implementation 'com.github.Jamshid-M:NestedRadioGroup:1.1'
}
```

## Attributes
### Only one attribute for disabling or enabling unselecting mode

Just specify flag in NestedRadioGroup
```
app:unselectable="true"
```

Open your project and setup NestedRadioGroup in your xml file, and add ViewGroups inside of RadioGroup.</br>
You have to include RadioButton inside of every ViewGroup or you will get an excetion with 
<b>You need to add radio button inside of NestedRadioGroup</b> message

```
<uz.jamshid.libarary.NestedRadioGroup
        android:id="@+id/rg"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:layout_margin="16dp"
        app:unselectable="true"
        >

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content">

            <RadioButton
                android:id="@+id/rb1"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:clickable="false"
                />

            <TextView
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:text="Voice"/>

            <ImageView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:src="@android:drawable/ic_btn_speak_now"/>
        </LinearLayout>

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content">

            <RadioButton
                android:id="@+id/rb2"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:clickable="false"
                />

            <TextView
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:text="Add"/>

            <ImageView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:src="@android:drawable/ic_input_add"/>

        </LinearLayout>
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content">

            <RadioButton
                android:id="@+id/rb3"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:clickable="false"
                />

            <TextView
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:layout_weight="1"
                android:text="Delete"/>

            <ImageView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:src="@android:drawable/ic_delete"/>
        </LinearLayout>
    </uz.jamshid.libarary.NestedRadioGroup>
```
    
You can handle SelectListener with lambda or in simple kotlin/java way</br>

```
rg.setRadioGroupListener({
            Log.d("NestedRadioGroup", "select")
        }, {
            Log.d("NestedRadioGroup", "unSelect")
        },{
            Log.d("NestedRadioGroup", "reSelect")
        })
```
    
NestedRadioGroup have 3 callbacks</br> 
<b>onSelect</br>
onUnSelect</br>
onReSelect</br></b>

They all returns ViewGroup of RadioButton as parameter

You can also listen for only one callback, just by setting explicitly in lambda
```
rg.setRadioGroupListener(onUnSelect = {
            Log.d("NestedRadioGroup", "onUnSelect")
        })
```

