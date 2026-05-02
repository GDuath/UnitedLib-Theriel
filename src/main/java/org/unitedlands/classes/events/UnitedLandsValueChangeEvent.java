package org.unitedlands.classes.events;

public class UnitedLandsValueChangeEvent extends UnitedLandsBaseEvent {

    private Object originalValue;
    private Object newValue;

    public UnitedLandsValueChangeEvent() {

    }

    public UnitedLandsValueChangeEvent(String key) {
        super(key);
    }

    public UnitedLandsValueChangeEvent(String key, Object originalValue) {
        super(key);
        this.originalValue = originalValue;
        this.newValue = originalValue;
    }

    public Object getOriginalValue() {
        return originalValue;
    }

    public void setOriginalValue(Object originalValue) {
        this.originalValue = originalValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public void setNewValue(Object newValue) {
        this.newValue = newValue;
    }

}
