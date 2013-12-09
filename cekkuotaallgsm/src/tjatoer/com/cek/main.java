package tjatoer.com.cek;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

public class main extends Activity {

    String SENT = "SMS_SENT";
    String DELIVERED = "SMS_DELIVERED";
    ImageButton btnAxis, btnTri,btnTsel, btnXl, btnSf, btnIm3;
    private AdView adView;
    LinearLayout layout;
		   //...rest of code

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alt2);
		
	/**	 layout = (LinearLayout) findViewById(R.id.linear);
		   AdView ad = new AdView(main.this, AdSize.BANNER, "YOUR_PUBLISHER_ID");
		        layout.addView(ad);
		        ad.loadAd(new AdRequest());  **/  

		addListenerOnButton();
	}

	public void addListenerOnButton() {

		final Context context = this;
//		adView = new AdView(this, AdSize.BANNER, <strong>a15298d3516ed3b</strong>);

		btnAxis = (ImageButton) findViewById(R.id.axis2);
        btnTri = (ImageButton) findViewById(R.id.tri2);
		btnTsel = (ImageButton) findViewById(R.id.tsel2);
        btnXl = (ImageButton) findViewById(R.id.xl2);
        btnSf=(ImageButton)findViewById(R.id.smartfren);
        btnIm3=(ImageButton)findViewById(R.id.im3);

        AdView adView = (AdView)this.findViewById(R.id.adview);
        adView.loadAd(new AdRequest());

        final PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
                new Intent(SENT), 0);

        final PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
                new Intent(DELIVERED), 0);

        //---when the SMS has been sent--- is working alright
        registerReceiver(new BroadcastReceiver()
        {
            public void onReceive(Context arg0, Intent arg1)
            {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS terkirim",
                                Toast.LENGTH_SHORT).show();

                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getBaseContext(), "Generic failure",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(getBaseContext(), "Tidak ada layanan",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(getBaseContext(), "Null PDU",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(getBaseContext(), "Jaringan mati",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
                unregisterReceiver(this);
            }
        }, new IntentFilter(SENT));

        //---when the SMS has been delivered--- this part is not working
        registerReceiver(new BroadcastReceiver()
        {


            @Override
            public void onReceive(Context arg0, Intent arg1)
            {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS diterima",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(getBaseContext(), "SMS tidak diterima",
                                Toast.LENGTH_SHORT).show();
                        break;

                    default :
                        Toast.makeText(getBaseContext(), "Gagal membuat laporan pengiriman",
                                Toast.LENGTH_SHORT).show();
                }
                unregisterReceiver(this);
            }
        }, new IntentFilter(DELIVERED));

		btnAxis.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setMessage("Pilih Metode").setTitle("Perhatian!")
                        .setPositiveButton("Internet", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent (context, axis.class) ;
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("USSD/Panggilan",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SmsManager sm= SmsManager.getDefault();
                                sm.sendTextMessage("123",null,"KUOTA", sentPI, deliveredPI);
                                AlertDialog.Builder builder1=new AlertDialog.Builder(context);
                                builder1.setMessage("Silahkan tunggu balasan dari operator!")
                                        .setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                                builder1.create().show();
                            }
                        });
                builder.create().show();
			}

		});
		
	    btnTri.setOnClickListener(new OnClickListener() {

		    @Override
		    public void onClick(View arg0) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setMessage("Pilih Metode").setTitle("Perhatian!")
                        .setPositiveButton("Internet", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent (context, tri.class) ;
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("USSD/Panggilan",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                AlertDialog.Builder builder1=new AlertDialog.Builder(context)
                                        .setMessage("Pilih paket").setTitle("Perhatian!")
                                        .setPositiveButton("Reguler",new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Intent intent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:*111*3*1*1"+Uri.encode("#")));
                                                startActivity(intent);
                                            }
                                        }).setNegativeButton("Download",new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Intent intent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:*111*3*1*2"+Uri.encode("#")));
                                                startActivity(intent);
                                            }
                                        });
                            }
                        })
                        .setNeutralButton("SMS",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SmsManager sm= SmsManager.getDefault();
                                sm.sendTextMessage("123",null,"INFO DATA", sentPI, deliveredPI);
                                AlertDialog.Builder builder1=new AlertDialog.Builder(context);
                                builder1.setMessage("Silahkan tunggu balasan dari operator!")
                                        .setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                                builder1.create().show();
                            }
                        });
                builder.create().show();
		    }

		});

        btnTsel.setOnClickListener(new OnClickListener() {

		    @Override
		    public void onClick(View arg0) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setMessage("Pilih Metode").setTitle("Perhatian!")
                        .setPositiveButton("Internet", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent (context, tsel.class) ;
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("SMS",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SmsManager sm= SmsManager.getDefault();
                                sm.sendTextMessage("3636",null,"UL INFO", sentPI, deliveredPI);
                                AlertDialog.Builder builder1=new AlertDialog.Builder(context);
                                builder1.setMessage("Silahkan tunggu balasan dari operator!")
                                        .setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                                builder1.create().show();
                            }
                        })
                        .setNeutralButton("USSD/Panggilan", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                AlertDialog.Builder builder1= new AlertDialog.Builder(context)
                                        .setTitle("Pilih paket anda").setItems(new CharSequence[]{"Volume Based"
                                                , "Unlimited", "Midnight", "Opera Mini", "Blackberry"}
                                                , new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                int a=1;
                                                if(i==1)a=2; else if(i==2)a=3; else if(i==3)a=4;
                                                else if(i==4)a=5;
                                                String ussd= "tel:*363*8*"+String.valueOf(a)+Uri.encode("#");
                                                Intent intent=new Intent(Intent.ACTION_CALL, Uri.parse(ussd));
                                                startActivity(intent);
                                            }
                                        });
                                builder1.create().show();
                            }
                        });
                builder.create().show();
		    }

		}); 

        btnXl.setOnClickListener(new OnClickListener() {

		    @Override
		    public void onClick(View arg0) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setMessage("Pilih Metode").setTitle("Perhatian!")
                        .setPositiveButton("Internet", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent (context, xl.class) ;
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("USSD/Panggilan",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:*123*7*5"+Uri.encode("#")));
                                startActivity(intent);
                            }
                        })
                        .setNeutralButton("SMS",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SmsManager sm= SmsManager.getDefault();
                                sm.sendTextMessage("868",null,"KUOTA", sentPI, deliveredPI);
                                AlertDialog.Builder builder1=new AlertDialog.Builder(context);
                                builder1.setMessage("Silahkan tunggu balasan dari operator!")
                                        .setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                                builder1.create().show();
                            }
                        });
                builder.create().show();
		    }

		});

        btnSf.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setMessage("Pilih Metode").setTitle("Perhatian!")
                        .setPositiveButton("Internet", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent (context, sf.class) ;
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("USSD/Panggilan",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:*995"));
                                startActivity(intent);
                            }
                        })
                        .setNeutralButton("SMS",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SmsManager sm= SmsManager.getDefault();
                                sm.sendTextMessage("995",null,"CEK", sentPI, deliveredPI);
                                AlertDialog.Builder builder1=new AlertDialog.Builder(context);
                                builder1.setMessage("Silahkan tunggu balasan dari operator!")
                                        .setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                                builder1.create().show();
                            }
                        });
                builder.create().show();
            }

        });

        btnIm3.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setMessage("Pilih Metode").setTitle("Perhatian!")
                        .setPositiveButton("Internet", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent (context, im3.class) ;
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("USSD/Panggilan",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:*363*5*1"+Uri.encode("#")));
                                startActivity(intent);
                            }
                        })
                        .setNeutralButton("SMS",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SmsManager sm= SmsManager.getDefault();
                                sm.sendTextMessage("363",null,"USAGE", sentPI, deliveredPI);
                                AlertDialog.Builder builder1=new AlertDialog.Builder(context);
                                builder1.setMessage("Silahkan tunggu balasan dari operator!")
                                        .setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                                builder1.create().show();
                            }
                        });
                builder.create().show();
            }

        });

    }

}
