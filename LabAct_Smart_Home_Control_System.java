import java.util.ArrayList;
import java.util.List;

public class SmartHomeControlSystem {
    public static void main(String[] args) {
    
        AirConditioner ac = new AirConditioner(3, 24);
        LampShade lamp1 = new LampShade(100, "yellow");
        LampShade lamp2 = new LampShade(lamp1);
        Television tv = new Television(1, 10);
        Microwave microwave = new Microwave();
    
        List<Device> devices = new ArrayList<>();
        devices.add(ac);
        devices.add(lamp1);
        devices.add(lamp2);
        devices.add(tv);
        devices.add(microwave);
    
        System.out.println("Initial count of powered on devices: " + Device.countPoweredOn(devices));
    
        Device.controlDevices(devices, true);
        System.out.println("\nAfter turning on all devices:");
        for (Device device : devices) {
            device.checkStatus();
        }
        System.out.println("\nCount of powered on devices: " + Device.countPoweredOn(devices));
      System.out.println("------------------------------");
        Device.controlDevices(devices, false);
        System.out.println("\nAfter turning off all devices:");
        for (Device device : devices) {
            device.checkStatus();
        }
        System.out.println("\nCount of powered on devices: " + Device.countPoweredOn(devices));

   System.out.println("------------------------------");
        System.out.println("\nDemonstrating setters:");
        ac.setTemperature(22);
        lamp1.setBrightness(80);
        tv.setChannel(5);
        microwave.setTimer(5);
    }
}
abstract class Device {
    private boolean isOn;

    public Device() {
        this.isOn = false;
    }

    public void turnOn() {
        this.isOn = true;
    }

    public void turnOff() {
        this.isOn = false;
    }

    public boolean isOn() {
        return isOn;
    }
    
    public abstract void checkStatus();
  
    public static void controlDevices(List<Device> devices, boolean turnOn) {
        for (Device device : devices) {
            if (turnOn) {
                device.turnOn();
            } else {
                device.turnOff();
            }
        }
    }
   
    public static int countPoweredOn(List<Device> devices) {
        int count = 0;
        for (Device device : devices) {
            if (device.isOn()) {
                count++;
            }
        }
        return count;
    }
}

class AirConditioner extends Device {
    private int fanSpeed;
    private int temperature;

    public AirConditioner(int fanSpeed, int temperature) {
        super();
        this.fanSpeed = fanSpeed;
        this.temperature = temperature;
    }

    public int getFanSpeed() {
        return fanSpeed;
    }

    public void setFanSpeed(int fanSpeed) {
        this.fanSpeed = fanSpeed;
        checkStatus();
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        checkStatus();
    }

    @Override
    public void checkStatus() {
        if (isOn()) {
            System.out.println("Air Conditioner is ON - Fan Speed: " + fanSpeed + ", Temperature: " + temperature);
        } else {
            System.out.println("Air Conditioner is OFF");
        }
    }
}

class LampShade extends Device {
    private int brightness;
    private String lightColor;

    public LampShade(int brightness, String lightColor) {
        super();
        this.brightness = brightness;
        this.lightColor = lightColor;
    }
 
    public LampShade(LampShade other) {
        super();
        this.brightness = other.brightness;
        this.lightColor = other.lightColor;
    }

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
        checkStatus();
    }

    public String getLightColor() {
        return lightColor;
    }

    public void setLightColor(String lightColor) {
        this.lightColor = lightColor;
        checkStatus();
    }

    @Override
    public void checkStatus() {
        if (isOn()) {
            System.out.println("Lamp Shade is ON - Brightness: " + brightness + "%, Light Color: " + lightColor);
        } else {
            System.out.println("Lamp Shade is OFF");
        }
    }
}

class Television extends Device {
    private int channel;
    private int volume;

    public Television(int channel, int volume) {
        super();
        this.channel = channel;
        this.volume = volume;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
        checkStatus();
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
        checkStatus();
    }

    @Override
    public void checkStatus() {
        if (isOn()) {
            System.out.println("Television is ON - Channel: " + channel + ", Volume: " + volume + "%");
        } else {
            System.out.println("Television is OFF");
        }
    }
}

class Microwave extends Device {
    private int timer;
    private int temperature;

    public Microwave() {
        super();
        this.timer = 0; 
        this.temperature = 0; 
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
        checkStatus();
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        checkStatus();
    }

    @Override
    public void checkStatus() {
        if (isOn()) {
            System.out.println("Microwave is ON - Timer: " + timer + " minutes, Temperature: " + temperature + "°C");
        } else {
            System.out.println("Microwave is OFF");
        }
    }
}