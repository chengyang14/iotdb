/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.iotdb.confignode.consensus.request.read.partition;

import org.apache.iotdb.common.rpc.thrift.TSeriesPartitionSlot;
import org.apache.iotdb.common.rpc.thrift.TTimePartitionSlot;
import org.apache.iotdb.confignode.consensus.request.ConfigPhysicalPlanType;
import org.apache.iotdb.confignode.rpc.thrift.TDataPartitionReq;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GetOrCreateDataPartitionPlan extends GetDataPartitionPlan {

  public GetOrCreateDataPartitionPlan() {
    super(ConfigPhysicalPlanType.GetOrCreateDataPartition);
  }

  public GetOrCreateDataPartitionPlan(
      Map<String, Map<TSeriesPartitionSlot, List<TTimePartitionSlot>>> partitionSlotsMap) {
    this();
    this.partitionSlotsMap = partitionSlotsMap;
  }

  /**
   * Convert TDataPartitionReq to GetOrCreateDataPartitionPlan
   *
   * @param req TDataPartitionReq
   * @return GetOrCreateDataPartitionPlan
   */
  public static GetOrCreateDataPartitionPlan convertFromRpcTDataPartitionReq(
      TDataPartitionReq req) {
    return new GetOrCreateDataPartitionPlan(new ConcurrentHashMap<>(req.getPartitionSlotsMap()));
  }
}
