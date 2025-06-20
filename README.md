# AntiFakeGps Plugin for Capacitor

Plugin Capacitor untuk mendeteksi apakah perangkat menggunakan Fake GPS (Mock Location) — khusus Android.

## Install

```bash
npm install @dhamaddam/anti-fake-gps
npx cap sync

// src/app/services/location-tracker.service.ts

import { Injectable, NgZone } from '@angular/core';
import { AlertController } from '@ionic/angular';
import { AntiFakeGps } from '@dhamaddam/anti-fake-gps';
import { Geolocation } from '@capacitor/geolocation';

@Injectable({
  providedIn: 'root'
})
export class LocationTrackerService {
  public lat: number = 0;
  public lng: number = 0;

  constructor(
    public zone: NgZone,
    private alertCtrl: AlertController
  ) {}

  async presentFakeGpsAlert() {
    const alert = await this.alertCtrl.create({
      header: 'Peringatan!',
      message: 'Kami mendeteksi penggunaan Fake GPS. Silakan nonaktifkan aplikasi tersebut.',
      buttons: ['OK']
    });
    await alert.present();
  }

  async checkMockLocationCapacitor(): Promise<boolean> {
    try {
      const position = await Geolocation.getCurrentPosition();
      const coords = position.coords;

      const result = await AntiFakeGps.getMock({
        latitude: coords.latitude,
        longitude: coords.longitude,
        accuracy: coords.accuracy,
        timestamp: position.timestamp
      });

      const results = result.results;
      let isMock = false;

      if (Array.isArray(results) && results.length > 0) {
        isMock = results[0].isMock;
        console.log('[AntiFakeGps Plugin] isMock:', isMock);
      } else {
        console.log('[AntiFakeGps Plugin] results empty or invalid');
      }

      return isMock;
    } catch (error) {
      console.error('[AntiFakeGps Plugin] Error:', error);
      return false;
    }
  }

  async startTracking(): Promise<{ location: { isFromMockProvider: boolean } }> {
    const isMock = await this.checkMockLo
