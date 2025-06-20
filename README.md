# 🛰️ AntiFakeGps Capacitor Plugin

[![npm version](https://img.shields.io/npm/v/@dhamaddam/anti-fake-gps?color=blue)](https://www.npmjs.com/package/@dhamaddam/anti-fake-gps)
[![license](https://img.shields.io/npm/l/@dhamaddam/anti-fake-gps.svg)](LICENSE)
[![capacitor](https://img.shields.io/badge/capacitor-android%20%7C%20web-green)](https://capacitorjs.com)

🔒 **AntiFakeGps** adalah plugin [Capacitor](https://capacitorjs.com/) untuk mendeteksi apakah perangkat menggunakan **Fake GPS / Mock Location** — saat ini mendukung **Android Native** dan fallback dummy untuk Web.

---

## 🚀 Fitur

- ✅ Deteksi langsung apakah lokasi berasal dari aplikasi Fake GPS
- ✅ Dukungan Android Native (`Location.isFromMockProvider`)
- ✅ Integrasi mudah di Ionic Angular, React, maupun vanilla Capacitor
- ✅ Peringatan real-time melalui UI Ionic (contoh alert disediakan)
- ✅ Dukungan fallback dummy di Web (selalu `false`)

---

## 📦 Instalasi

```bash
npm install @dhamaddam/anti-fake-gps
npx cap sync


import { AntiFakeGps } from '@dhamaddam/anti-fake-gps';

const result = await AntiFakeGps.getMock({
  latitude: -6.2,
  longitude: 106.8,
  accuracy: 5,
  timestamp: Date.now()
});

console.log('Is Fake Location?', result.results[0].isMock); // true / false

import { Injectable, NgZone } from '@angular/core';
import { AlertController } from '@ionic/angular';
import { AntiFakeGps } from '@dhamaddam/anti-fake-gps';
import { Geolocation } from '@capacitor/geolocation';

@Injectable({ providedIn: 'root' })
export class LocationTrackerService {
  constructor(private zone: NgZone, private alertCtrl: AlertController) {}

  async presentFakeGpsAlert() {
    const alert = await this.alertCtrl.create({
      header: 'Peringatan!',
      message: 'Kami mendeteksi penggunaan Fake GPS. Silakan nonaktifkan aplikasi tersebut.',
      buttons: ['OK']
    });
    await alert.present();
  }

  async checkMockLocation(): Promise<boolean> {
    try {
      const pos = await Geolocation.getCurrentPosition();
      const res = await AntiFakeGps.getMock({
        latitude: pos.coords.latitude,
        longitude: pos.coords.longitude,
        accuracy: pos.coords.accuracy,
        timestamp: pos.timestamp
      });

      const isMock = res.results?.[0]?.isMock ?? false;
      if (isMock) await this.presentFakeGpsAlert();

      return isMock;
    } catch (err) {
      console.error('[AntiFakeGps] Error:', err);
      return false;
    }
  }
}

