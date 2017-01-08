/*
 * Copyright (c) 2016-2017 Sviataslau Apanasionak
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * 3. Redistributions of source code or in binary form for commercial purposes are prohibited without written permission of the author.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.slavick.macmonitor.model.devices.dns;

import com.slavick.macmonitor.model.networkentities.IpAddress;

import javax.persistence.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by apanasyonok on 30.11.2016.
 */

@Embeddable
@Access(AccessType.FIELD)
public class DnsEntry {

    @Transient
    @Embedded
    private IpAddress ipAddress;

    @Column(name = "rev_dns",nullable = true)
    private String reverseDns;


    public DnsEntry(){}

    public DnsEntry(IpAddress ipAddress) {
        this.ipAddress = ipAddress;
        try {
            reverseDns = InetAddress.getByName(ipAddress.getString()).getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public IpAddress getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(IpAddress ipAddress) {
        this.ipAddress = ipAddress;
    }


    public static void main(String[] args) throws UnknownHostException {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DnsEntry)) return false;

        DnsEntry dnsEntry = (DnsEntry) o;

        if (ipAddress != null ? !ipAddress.equals(dnsEntry.ipAddress) : dnsEntry.ipAddress != null) return false;
        return reverseDns != null ? reverseDns.equals(dnsEntry.reverseDns) : dnsEntry.reverseDns == null;

    }

    @Override
    public int hashCode() {
        int result = ipAddress != null ? ipAddress.hashCode() : 0;
        result = 31 * result + (reverseDns != null ? reverseDns.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DnsEntry{" +
                "ipAddress=" + ipAddress +
                ", reverseDns='" + reverseDns + '\'' +
                '}';
    }
}
